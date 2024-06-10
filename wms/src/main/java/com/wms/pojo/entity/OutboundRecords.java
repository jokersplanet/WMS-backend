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
@ApiModel(value="OutboundRecords对象", description="出库")
public class OutboundRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "outbound_id", type = IdType.AUTO)
    private Integer outboundId;

    @ApiModelProperty("出库货物名称")
    private String goodsName;

    @ApiModelProperty("出库时间")
    private String time;

    @ApiModelProperty("出库数量")
    private Integer count;

    @ApiModelProperty("出库仓库")
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
