package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.sysUserRole.SysUserRoleQueryRequest;
import com.wms.pojo.entity.SysUserRole;
import com.wms.mapper.SysUserRoleMapper;
import com.wms.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-24
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public QueryWrapper<SysUserRole> getQueryWrapper(SysUserRoleQueryRequest sysUserRoleQueryRequest) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        if(sysUserRoleQueryRequest == null){
            return queryWrapper;
        }
        Integer id = sysUserRoleQueryRequest.getId();
        Integer userId = sysUserRoleQueryRequest.getUserId();
        Integer roleId = sysUserRoleQueryRequest.getRoleId();
        String sortField = sysUserRoleQueryRequest.getSortField();
        String sortOrder = sysUserRoleQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"id",id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId),"user_id",userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(roleId),"role_id",roleId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
