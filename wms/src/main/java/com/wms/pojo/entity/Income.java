package com.wms.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Date;

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
 * @since 2024-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Income对象", description="收入")
public class Income implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "income_id", type = IdType.AUTO)
    private Integer incomeId;

    @ApiModelProperty("收入时间")
    private Date time;

    @ApiModelProperty("收入金额")
    private BigDecimal value;

    @ApiModelProperty("收入来源")
    private String origin;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
