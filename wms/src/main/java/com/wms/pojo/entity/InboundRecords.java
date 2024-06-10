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
 *
 * @author lwh
 * @since 2024-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="InboundRecords对象", description="入库记录")
public class InboundRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inbound_id", type = IdType.AUTO)
    private Integer inboundId;

    @ApiModelProperty("入库时间")
    private String time;

    @ApiModelProperty("入库数量")
    private Long count;

    @ApiModelProperty("入库货物名称")
    private String goodsName;

    @ApiModelProperty("入库仓库")
    private Integer wareId;

    @ApiModelProperty("账户")
    private String userAccount;

    @ApiModelProperty("成本")
    private BigDecimal price;

    @ApiModelProperty("售价")
    private BigDecimal value;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
