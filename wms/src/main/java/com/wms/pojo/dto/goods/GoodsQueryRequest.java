package com.wms.pojo.dto.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-08 23:28
 */
@Data
public class GoodsQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private String goodsName;

    private String inboundTime;

    private String outboundTime;

    private Long count;

    private BigDecimal price;

    private BigDecimal value;

    private Long lowerLimit;

    private Long upperLimit;

    private Integer classId;

    private Integer unitId;

    private Integer cateId;

    private Integer wareId;
}
