package com.wms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-13 16:50
 */
@Data
public class OutboundRecordsVO implements Serializable {

    private Integer outboundId;

    private GoodsVO goodsVO;

    private WarehouseVO warehouseVO;

    private UserVO userVO;
}
