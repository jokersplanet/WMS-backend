package com.wms.pojo.dto.department;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-28 11:19
 */
@Data
public class DepartmentQueryRequest extends QueryPageParam implements Serializable {
    private Integer id;
    private String departmentName;
    private static final long serialVersionUID = 1L;
}
