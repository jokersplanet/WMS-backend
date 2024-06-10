package com.wms.pojo.dto.orders;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 15:04
 */
@Data
public class OrdersDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;
}
