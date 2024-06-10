package com.wms.pojo.entity;

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
@ApiModel(value="Unit对象", description="")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "unit_id", type = IdType.AUTO)
    private Integer unitId;

    @ApiModelProperty("单位名")
    private String unitName;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
