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
@ApiModel(value="ScrapRecords对象", description="废弃记录")
public class ScrapRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scrap_id", type = IdType.AUTO)
    private Integer scrapId;

    @ApiModelProperty("仓库id")
    private Integer wareId;

    @ApiModelProperty("废弃订单货物名称")
    private String goodsName;

    @ApiModelProperty("数量")
    private Long count;

    @ApiModelProperty("废弃订单时间")
    private String time;

    @ApiModelProperty("成本")
    private BigDecimal price;

    @ApiModelProperty("售价")
    private BigDecimal value;

    @ApiModelProperty("账户")
    private String userAccount;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
