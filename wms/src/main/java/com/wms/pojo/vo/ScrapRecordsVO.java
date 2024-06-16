package com.wms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-13 16:54
 */
@Data
public class ScrapRecordsVO implements Serializable {

    private Integer scrapId;

    private GoodsVO goodsVO;

    private WarehouseVO warehouseVO;

    private UserVO userVO;
}
