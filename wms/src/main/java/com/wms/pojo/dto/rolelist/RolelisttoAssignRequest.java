package com.wms.pojo.dto.rolelist;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-24 23:15
 */
@Data
public class RolelisttoAssignRequest implements Serializable {

    private Integer userId;

    private static final long serialVersionUID = 1L;
}
