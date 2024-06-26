package com.wms.service.impl;

import com.wms.config.SecurityConfig.custom.CustomUser;
import com.wms.config.SecurityConfig.custom.UserDetailsService;
import com.wms.pojo.entity.User;
import com.wms.service.SysPermsService;
import com.wms.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lwh
 * @create 2024-06-25 17:23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private SysPermsService sysPermsService;

    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getByUserAccount(username);
//        if(user == null){
//            throw new UsernameNotFoundException("该用户不存在！");
//        }
//        return new CustomUser(user,Collections.emptyList());
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在！");
        }
        //根据用户userid查询用户操作权限数据
        List<String> userPermsList = sysPermsService.findUserPermsByUserId(Long.valueOf(user.getUid()));
        //创建该list集合用于封装最终权限数据
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        //查询list集合遍历
        for (String perm : userPermsList) {
            authList.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(user, authList);
    }
}
