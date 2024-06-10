package com.wms.pojo.dto.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-09 15:44
 */
@Data
public class GoodsUpdateRequest implements Serializable {

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
