package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.sysUserRole.SysUserRoleQueryRequest;
import com.wms.pojo.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-24
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    QueryWrapper<SysUserRole> getQueryWrapper(SysUserRoleQueryRequest sysUserRoleQueryRequest);
}
