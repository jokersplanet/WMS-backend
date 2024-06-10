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
 * 货物大类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Category对象", description="货物大类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cate_id", type = IdType.AUTO)
    private Integer cateId;

    @ApiModelProperty("大类名")
    private String cateName;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
