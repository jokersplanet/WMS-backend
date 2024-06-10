package com.wms.pojo.dto.department;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-28 20:58
 */
@Data
public class DepartmentAddRequest implements Serializable {
    private String dptName;

    private static final long serialVersionUID = 1L;
}
