package com.wms.pojo.entity;

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
 * 
 * </p>
 *
 * @author lwh
 * @since 2024-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Status对象", description="状态")
public class Status implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("状态名")
    private String name;

    @ApiModelProperty("顺序")
    private String sequence;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
