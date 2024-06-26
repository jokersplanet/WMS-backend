package com.wms.pojo.dto.sysUserRole;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-26 21:38
 */
@Data
public class SysUserRoleAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;
}
