package com.wms.pojo.entity;

import java.io.Serializable;
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
 * @since 2024-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRolePerms对象", description="")
public class SysRolePerms implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roleId;

    private Integer permsId;

    private Integer isDeleted;


}
