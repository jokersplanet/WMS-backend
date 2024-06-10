package com.wms.pojo.dto.typeclass;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 20:23
 */
@Data
public class TypeclassQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer classId;

    private String className;

    private Integer categoryId;
}
