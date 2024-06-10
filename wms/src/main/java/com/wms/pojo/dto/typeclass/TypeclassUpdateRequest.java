package com.wms.pojo.dto.typeclass;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 20:40
 */
@Data
public class TypeclassUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer classId;

    private String className;

    private Integer categoryId;
}
