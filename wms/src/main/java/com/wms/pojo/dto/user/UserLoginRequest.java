package com.wms.pojo.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-27 17:19
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;

    private Integer uid;

    private String userAccount;

    private String userName;

    private String userPassword;
}
