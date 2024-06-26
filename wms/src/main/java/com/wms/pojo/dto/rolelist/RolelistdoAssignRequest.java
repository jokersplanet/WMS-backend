package com.wms.pojo.dto.rolelist;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lwh
 * @create 2024-06-24 21:04
 */
@Data
public class RolelistdoAssignRequest implements Serializable {

    //用户id
    private Integer userId;

    //角色id列表
    private List<Integer> roleIdList;

    private static final long serialVersionUID = 1L;
}
