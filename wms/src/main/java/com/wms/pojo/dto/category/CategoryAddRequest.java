package com.wms.pojo.dto.category;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 21:05
 */
@Data
public class CategoryAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cateName;
}
