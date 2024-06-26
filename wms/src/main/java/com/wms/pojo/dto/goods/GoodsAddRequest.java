package com.wms.pojo.dto.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-09 0:16
 */
@Data
public class GoodsAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String goodsName;

    private String inboundTime;

    private String outboundTime;

    private Long count;

    private BigDecimal price;

    private BigDecimal value;

    private Long lowerLimit;

    private Long upperLimit;

    private Integer unitId;

    private Integer wareId;

    private Integer upperClassId;

    private Integer lowerClassId;
}
