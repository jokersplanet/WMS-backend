package com.wms.pojo.dto.user;

import lombok.Data;

/**
 * @author lwh
 * @create 2024-05-26 16:15
 */
@Data
public class UserRegisterRequest {
    private static final long serialVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
