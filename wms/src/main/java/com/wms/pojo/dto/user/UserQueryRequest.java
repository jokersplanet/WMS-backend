package com.wms.pojo.dto.user;

import com.wms.common.QueryPageParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-27 23:57
 */
@Data
public class UserQueryRequest extends QueryPageParam implements Serializable {
    /**
     * uid
     */
    private Integer uid;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户电话
     */
    private String phone;


    /**
     * 用户角色：user/admin/ban
     */
    private String role;

    /**
     * 所属部门
     */
    private Integer department;

    private static final long serialVersionUID = 1L;
}
