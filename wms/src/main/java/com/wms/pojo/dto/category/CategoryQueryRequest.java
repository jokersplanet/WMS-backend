package com.wms.pojo.dto.category;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 20:54
 */
@Data
public class CategoryQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cateId;
}
