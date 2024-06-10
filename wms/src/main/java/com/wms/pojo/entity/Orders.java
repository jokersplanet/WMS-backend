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
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty("货物名称")
    private String goodsName;

    @ApiModelProperty("成本")
    private BigDecimal price;

    @ApiModelProperty("售价")
    private BigDecimal value;

    @ApiModelProperty("数量")
    private Integer count;

    @ApiModelProperty("时间")
    private String time;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
