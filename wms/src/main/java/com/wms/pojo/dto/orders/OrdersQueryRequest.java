package com.wms.pojo.dto.orders;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-08 14:19
 */
@Data
public class OrdersQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private Integer goodsId;

    private Integer status;

    private Date startDate;

    private Date endDate;
}
