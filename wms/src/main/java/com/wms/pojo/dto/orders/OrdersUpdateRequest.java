package com.wms.pojo.dto.orders;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-08 15:21
 */
@Data
public class OrdersUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private String goodsName;

    private BigDecimal price;

    private BigDecimal value;

    private Integer count;

    private String time;

    private Integer status;

    private String notes;
}
