package com.wms.pojo.dto.orders;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-08 15:00
 */
@Data
public class OrdersAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String goodsName;

    private BigDecimal price;

    private BigDecimal value;

    private Integer count;

    private String time;

    private Integer status;

    private String notes;
}
