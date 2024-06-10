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
 * <p>
 * 
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Warehouse对象", description="仓库")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ware_id", type = IdType.AUTO)
    private Integer wareId;

    @ApiModelProperty("仓库负责人(对应账户名)")
    private String wareHead;

    @ApiModelProperty("仓库名")
    private String wareName;

    @ApiModelProperty("仓库价值")
    private BigDecimal wareValue;

    @ApiModelProperty("备注")
    private String wareNotes;

    @ApiModelProperty("地址")
    private String wareAddress;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
