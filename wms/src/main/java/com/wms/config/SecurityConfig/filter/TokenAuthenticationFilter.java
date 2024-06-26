package com.wms.config.SecurityConfig.filter;

import com.alibaba.fastjson.JSON;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.utils.JwtHelper;
import com.wms.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 判断是否有登录
 * @author lwh
 * @create 2024-06-25 20:09
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        logger.info("uri:" + request.getRequestURI());
        //如果是登录接口，不需要判断直接放行
        if("/user/login".equals(request.getRequestURI())){
            filterChain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        if(authenticationToken == null){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }else{
            ResponseUtil.out(response, Response.error(ErrorCode.LOGIN_ERROR));
        }
    }

    //判断请求头里有没有信息
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader("token");
        logger.info("token:" + token);
        if(!StringUtils.isEmpty(token)){
            String userAccount = JwtHelper.getUserAccount(token);
            logger.info("userAccount:" + userAccount);
            if(!StringUtils.isEmpty(userAccount)){
                //通过用户名称从redis获取权限数据
                String authString  = (String) redisTemplate.opsForValue().get(userAccount);
                //把redis获取字符串权限数据转换要求集合类型List<SimpleGrantedAuthority>
                if(!StringUtils.isEmpty(authString)){
                    List<Map> mapList = JSON.parseArray(authString, Map.class);
                    List<SimpleGrantedAuthority> authList = new ArrayList<>();
                    for(Map map : mapList){
                        String authority = (String) map.get("authority");
                        authList.add(new SimpleGrantedAuthority(authority));
                    }
                    return new UsernamePasswordAuthenticationToken(userAccount,null, authList);
                }else{
                    return new UsernamePasswordAuthenticationToken(userAccount,null, new ArrayList<>());
                }
            }
        }
        return null;
    }
}
