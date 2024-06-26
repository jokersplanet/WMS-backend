package com.wms.pojo.dto.sysPerms;

import com.google.common.collect.PeekingIterator;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-25 10:48
 */
@Data
public class SysPermsQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer parentId;

    private String name;

    private String perms;
}
