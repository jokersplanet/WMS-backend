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
 * @author wenhui
 * @since 2024-05-20
 */
@Data
@ApiModel(value="User对象", description="用户")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("uid")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty("账户")
    private String userAccount;

    @ApiModelProperty("用户名/昵称")
    private String userName;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("部门")
    private Integer department;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;


}
