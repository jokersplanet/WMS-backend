package com.wms.pojo.vo;

import com.wms.service.GoodsService;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-13 16:34
 */
@Data
public class InboundRecordsVO implements Serializable {

    private Integer inboundId;

    private GoodsVO goodsVO;

    private WarehouseVO warehouseVO;

    private UserVO userVO;
}
