package com.wms.pojo.dto.department;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-05 21:48
 */
@Data
public class DepartmentDeleteRequest implements Serializable {
    private Integer dptId;

    private static final long serialVersionUID = 1L;
}
