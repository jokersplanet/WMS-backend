package com.wms.pojo.dto.rolelist;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-17 15:34
 */
@Data
public class RolelistUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String roleName;

    private String notes;
}
