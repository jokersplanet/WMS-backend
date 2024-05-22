package com.wms.model.common;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author lwh
 * @create 2024-05-21 19:41
 */
@Data
public class QueryPageParam {
    //默认个数
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    private HashMap data;//存放数据库数据
}
