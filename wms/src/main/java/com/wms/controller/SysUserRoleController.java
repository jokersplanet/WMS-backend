package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.sysPerms.SysPermsAddRequest;
import com.wms.pojo.dto.sysPerms.SysPermsQueryRequest;
import com.wms.pojo.dto.sysUserRole.SysUserRoleAddRequest;
import com.wms.pojo.dto.sysUserRole.SysUserRoleQueryRequest;
import com.wms.pojo.entity.SysPerms;
import com.wms.pojo.entity.SysUserRole;
import com.wms.service.RolelistService;
import com.wms.service.SysUserRoleService;
import com.wms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwh
 * @since 2024-06-24
 */
@RestController
@RequestMapping("/sys-user-role")
public class SysUserRoleController {


    /**
     * 分页获取用户角色分配信息
     * 在rolelist接口中有根据用户id获取角色的方法
     */

    /**
     * 为用户分配角色
     * 在rolelist接口中
     */



}
