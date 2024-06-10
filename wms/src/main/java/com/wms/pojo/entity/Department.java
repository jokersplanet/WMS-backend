package com.wms.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author wenhui
 * @since 2024-05-28
 */
@Data
@ApiModel(value="Department对象", description="部门")
public class Department implements Serializable {

    @ApiModelProperty("部门id")
    @TableId(value = "dpt_id", type = IdType.AUTO)
    private Integer dptId;

    @ApiModelProperty("部门名称")
    private String dptName;

    @ApiModelProperty("注解")
    private String dptNotes;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
