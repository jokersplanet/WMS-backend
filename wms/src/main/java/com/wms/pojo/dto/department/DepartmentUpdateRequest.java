package com.wms.pojo.dto.department;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-05 22:59
 */
@Data
public class DepartmentUpdateRequest implements Serializable {

    private Integer dptId;

    private String dptName;

    private static final long serialVersionUID = 1L;

}
