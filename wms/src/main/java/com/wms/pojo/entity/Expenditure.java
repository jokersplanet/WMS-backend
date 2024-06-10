package com.wms.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 开支
 * </p>
 *
 * @author lwh
 * @since 2024-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Expenditure对象", description="开支")
public class Expenditure implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "exp_id", type = IdType.AUTO)
    private Integer expId;

    @ApiModelProperty("开支时间")
    private String time;

    @ApiModelProperty("开支金额")
    private BigDecimal value;

    @ApiModelProperty("开支地点")
    private String destination;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;




}
