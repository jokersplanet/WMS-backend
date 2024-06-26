package com.wms.pojo.dto.sysPerms;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-25 10:58
 */
@Data
public class SysPermsAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer parentId;

    private String name;

    private String perms;
}
