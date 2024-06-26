package com.wms.config.SecurityConfig.custom;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author lwh
 * @create 2024-06-25 17:01
 */
public class CustomUser extends User {

    private com.wms.pojo.entity.User user;

    public CustomUser(com.wms.pojo.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUserAccount(), user.getUserPassword(), authorities);
        this.user = user;
    }

    public com.wms.pojo.entity.User getUser() {
        return user;
    }

    public void setUser(com.wms.pojo.entity.User user) {
        this.user = user;
    }
}
