package com.wms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-13 16:57
 */
@Data
public class OrdersVO implements Serializable {

    private Integer orderId;

    private GoodsVO goodsVO;

    private StatusVO statusVO;
}
