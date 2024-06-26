package com.wms.config.SecurityConfig.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.config.SecurityConfig.custom.CustomUser;
import com.wms.pojo.dto.user.UserLoginRequest;
import com.wms.utils.JwtHelper;
import com.wms.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 完成认证
 * @author lwh
 * @create 2024-06-25 19:37
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisTemplate redisTemplate;

    //构造方法
    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login","POST"));

        this.redisTemplate = redisTemplate;
    }
    //登录认证
    //获取输入的用户名和密码，调用方法认证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            //获取用户信息
            UserLoginRequest userLoginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
            //封装对象
           Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword());
           //调用方法完成认证
           return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //认证成功调用方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //获取当前用户
        CustomUser customUser = (CustomUser)authResult.getPrincipal();
        //生成token
        String token = JwtHelper.createToken(Long.valueOf(customUser.getUser().getUid()), customUser.getUser().getUserAccount());

        //获取当前用户权限数据，放到Redis里：key(username)——value(权限数据)
        redisTemplate.opsForValue().set(customUser.getUser().getUserAccount(), JSON.toJSONString(customUser.getAuthorities()));

        //返回
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        ResponseUtil.out(response, Response.success(map));
    }
    //认证失败调用方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response,Response.error(ErrorCode.LOGIN_ERROR));
    }
}
