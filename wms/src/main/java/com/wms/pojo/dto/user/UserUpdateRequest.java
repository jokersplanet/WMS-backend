package com.wms.pojo.dto.user;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-27 23:40
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * uid
     */
    private Integer uid;


    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;


    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户角色：user/admin
     */
    private String role;

    private Integer departmentId;

    private static final long serialVersionUID = 1L;
}
