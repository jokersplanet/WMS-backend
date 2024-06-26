package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.rolelist.RolelistdoAssignRequest;
import com.wms.pojo.dto.rolelist.RolelistQueryRequest;
import com.wms.pojo.entity.Rolelist;
import com.wms.mapper.RolelistMapper;
import com.wms.pojo.entity.SysUserRole;
import com.wms.service.RolelistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.service.SysUserRoleService;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-16
 */
@Service
public class RolelistServiceImpl extends ServiceImpl<RolelistMapper, Rolelist> implements RolelistService {

    private SysUserRoleService sysUserRoleService;

    @Override
    public Wrapper<Rolelist> getQueryWrapper(RolelistQueryRequest rolelistQueryRequest) {
        QueryWrapper<Rolelist> queryWrapper = new QueryWrapper<>();
        if(rolelistQueryRequest == null){
            return queryWrapper;
        }
        Integer roleId = rolelistQueryRequest.getRoleId();
        String roleName = rolelistQueryRequest.getRoleName();
        String notes = rolelistQueryRequest.getNotes();
        String sortField = rolelistQueryRequest.getSortField();
        String sortOrder = rolelistQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(roleId),"role_id",roleId);
        queryWrapper.eq(StringUtils.isNotBlank(roleName),"role_name",roleName);
        queryWrapper.eq(StringUtils.isNotBlank(notes),"notes",notes);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public Map<String, Object> findRoleDataByUserId(Integer userId) {
        //查询所有角色
        List<Rolelist> allRolelists = baseMapper.selectList(null);
        //查询userId对应的所有角色id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);
        List<Integer> existRoleIdList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());
        List<Rolelist> assignRoleList = new ArrayList<>();
        for(Rolelist rolelist : allRolelists){
            if(existRoleIdList.contains(rolelist.getRoleId())){
                assignRoleList.add(rolelist);
            }
        }
        Map<String,Object> roleMap = new HashMap<>();
        roleMap.put("assignRoleList",assignRoleList);
        roleMap.put("allRolelists",allRolelists);
        return roleMap;
    }

    @Override
    public void doAssign(RolelistdoAssignRequest rolelistAssignRequest) {
        //删除用户之前分配的角色
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getRoleId,rolelistAssignRequest.getUserId());
        sysUserRoleService.remove(wrapper);
        //重新进行分配
        List<Integer> roleIdList = rolelistAssignRequest.getRoleIdList();
        for(Integer roleId : roleIdList){
            if (ObjectUtils.isEmpty(roleId)) {
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(rolelistAssignRequest.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleService.save(sysUserRole);
        }
    }
}
