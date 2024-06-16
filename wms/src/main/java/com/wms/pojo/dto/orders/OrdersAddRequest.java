package com.wms.pojo.dto.orders;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-08 15:00
 */
@Data
public class OrdersAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private BigDecimal price;

    private BigDecimal value;

    private Integer count;

    private Date time;

    private Integer status;

    private String notes;
}
