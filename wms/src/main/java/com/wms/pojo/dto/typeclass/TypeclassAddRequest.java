package com.wms.pojo.dto.typeclass;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 20:29
 */
@Data
public class TypeclassAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String className;

    private Integer categoryId;
}
