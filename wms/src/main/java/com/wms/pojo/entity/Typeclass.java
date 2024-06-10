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
 * 货物种类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Typeclass对象", description="货物种类(小类)")
public class Typeclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty("小类名")
    private String className;

    @ApiModelProperty("大类id")
    private Integer categoryId;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
