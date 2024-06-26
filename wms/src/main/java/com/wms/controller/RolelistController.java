package com.wms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.rolelist.*;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.Rolelist;
import com.wms.service.RolelistService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 所有人员可以对角色信息进行查询
 * 但仅限超级管理员可对角色进行增、删、改的操作
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwh
 * @since 2024-06-16
 */
@RestController
@RequestMapping("/rolelist")
public class RolelistController {

    @Resource
    private RolelistService rolelistService;

    /**
     * 根据用户id获取角色
     * @param
     * @return
     */
    @PostMapping("/toAssign")
    @PreAuthorize("hasAuthority('rolelist.toAssign')")
//    @Transactional
    public Response toAssignRole(@RequestBody RolelisttoAssignRequest rolelisttoAssignRequest){
        Map<String,Object> map = rolelistService.findRoleDataByUserId(rolelisttoAssignRequest.getUserId());
        return Response.success(map);
    }

    /**
     * 为用户分配角色
     * @param rolelistdoAssignRequest
     * @return
     */
    @PostMapping("/doAssign")
    @PreAuthorize("hasAuthority('rolelist.doAssign')")
    public Response doAssign(@RequestBody RolelistdoAssignRequest rolelistdoAssignRequest){
        rolelistService.doAssign(rolelistdoAssignRequest);
        return Response.success();
    }



    /**
     * 分页获取角色信息
     * @param rolelistQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('rolelist.listPage')")
    public Response<Page<Rolelist>> listRoleListByPage(@RequestBody RolelistQueryRequest rolelistQueryRequest){
        int current = rolelistQueryRequest.getCurrent();
        int size = rolelistQueryRequest.getPageSize();
        Page<Rolelist> page = rolelistService.page(new Page<>(current, size), rolelistService.getQueryWrapper(rolelistQueryRequest));
        return Response.success(page);
    }

    /**
     * 增加角色
     * @param rolelistAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('rolelist.add')")
    public Response<Integer> rolelistAdd(@RequestBody RolelistAddRequest rolelistAddRequest){
        if(rolelistAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Rolelist rolelist = new Rolelist();
        BeanUtils.copyProperties(rolelistAddRequest,rolelist);
        boolean save = rolelistService.save(rolelist);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return Response.success(rolelist.getRoleId());
    }

    /**
     * 删除角色
     * @param rolelistDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('rolelist.delete')")
    public Response<Boolean> rolelistDelete(@RequestBody RolelistDeleteRequest rolelistDeleteRequest){
        if(rolelistDeleteRequest == null || rolelistDeleteRequest.getRoleId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = rolelistService.removeById(rolelistDeleteRequest.getRoleId());
        return Response.success(result);
    }

    /**
     * 更新角色记录
     * @param rolelistUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('rolelist.update')")
    public Response<Boolean> rolelistUpdate(@RequestBody RolelistUpdateRequest rolelistUpdateRequest){
        if(rolelistUpdateRequest == null || rolelistUpdateRequest.getRoleId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Rolelist rolelist = new Rolelist();
        BeanUtils.copyProperties(rolelistUpdateRequest,rolelist);
        boolean result = rolelistService.updateById(rolelist);
        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改角色记录失败");
        }
        return Response.success(result);
    }
}
