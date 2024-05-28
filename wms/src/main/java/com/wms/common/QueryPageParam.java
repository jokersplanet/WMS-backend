package com.wms.common;

import com.wms.constant.CommonConstant;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author lwh
 * @create 2024-05-21 19:41
 */
@Data
public class QueryPageParam {
    //页面大小
    private  int pageSize = 10;
    //当前页号默认为1
    private  int current = 1;
    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
