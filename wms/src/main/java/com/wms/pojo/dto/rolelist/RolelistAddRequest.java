package com.wms.pojo.dto.rolelist;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-17 15:12
 */
@Data
public class RolelistAddRequest implements Serializable {

    private String roleName;

    private String notes;

    private static final long serialVersionUID = 1L;
}
