package com.wms.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Goods对象", description="货物")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty("货物名称")
    private String goodsName;

    @ApiModelProperty("入库时间")
    private String inboundTime;

    @ApiModelProperty("出库时间")
    private String outboundTime;

    @ApiModelProperty("数量")
    private Long count;

    @ApiModelProperty("成本")
    private BigDecimal price;

    @ApiModelProperty("售价")
    private BigDecimal value;

    @ApiModelProperty("最低限制")
    private Long lowerLimit;

    @ApiModelProperty("最高限制")
    private Long upperLimit;

    @ApiModelProperty("小类id")
    private Integer classId;

    @ApiModelProperty("单位id")
    private Integer unitId;

    @ApiModelProperty("大类id")
    private Integer cateId;

    @ApiModelProperty("仓库id")
    private Integer wareId;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
