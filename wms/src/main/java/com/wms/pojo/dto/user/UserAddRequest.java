package com.wms.pojo.dto.user;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-27 23:30
 */
public class UserAddRequest implements Serializable {
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户角色：user/admin/ban
     */
    private String role;


    private static final long serialVersionUID = 1L;
}
