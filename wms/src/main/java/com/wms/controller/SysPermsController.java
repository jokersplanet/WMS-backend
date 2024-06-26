package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.rolelist.RolelistAddRequest;
import com.wms.pojo.dto.rolelist.RolelistDeleteRequest;
import com.wms.pojo.dto.rolelist.RolelistQueryRequest;
import com.wms.pojo.dto.rolelist.RolelistUpdateRequest;
import com.wms.pojo.dto.sysPerms.SysPermsAddRequest;
import com.wms.pojo.dto.sysPerms.SysPermsDeleteRequest;
import com.wms.pojo.dto.sysPerms.SysPermsQueryRequest;
import com.wms.pojo.dto.sysPerms.SysPermsUpdateRequest;
import com.wms.pojo.entity.Rolelist;
import com.wms.pojo.entity.SysPerms;
import com.wms.service.SysPermsService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwh
 * @since 2024-06-25
 */
@RestController
@RequestMapping("/sys-perms")
public class SysPermsController {

    private SysPermsService sysPermsService;

    /**
     * 分页获取权限信息
      * @param sysPermsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('sysPerms.listPage')")
    public Response<Page<SysPerms>> listPermsByPage(@RequestBody SysPermsQueryRequest sysPermsQueryRequest){
        int current = sysPermsQueryRequest.getCurrent();
        int size = sysPermsQueryRequest.getPageSize();
        Page<SysPerms> page = sysPermsService.page(new Page<>(current, size), sysPermsService.getQueryWrapper(sysPermsQueryRequest));
        return Response.success(page);
    }

    /**
     * 增加权限记录
     * @param sysPermsAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sysPerms.add')")
    public Response<Integer> permsAdd(@RequestBody SysPermsAddRequest sysPermsAddRequest){
        if(sysPermsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        SysPerms sysPerms = new SysPerms();
        BeanUtils.copyProperties(sysPermsAddRequest,sysPerms);
        boolean save = sysPermsService.save(sysPerms);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return Response.success(sysPerms.getId());
    }

    /**
     * 删除权限记录
     * @param sysPermsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sysPerms.delete')")
    public Response<Boolean> permsDelete(@RequestBody SysPermsDeleteRequest sysPermsDeleteRequest){
        if(sysPermsDeleteRequest == null || sysPermsDeleteRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = sysPermsService.removeById(sysPermsDeleteRequest.getId());
        return Response.success(result);
    }

    /**
     * 更新权限记录
     * @param sysPermsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sysPerms.update')")
    public Response<Boolean> permsUpdate(@RequestBody SysPermsUpdateRequest sysPermsUpdateRequest){
        if(sysPermsUpdateRequest == null || sysPermsUpdateRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        SysPerms sysPerms = new SysPerms();
        BeanUtils.copyProperties(sysPermsUpdateRequest,sysPerms);
        boolean result = sysPermsService.updateById(sysPerms);
        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改角色记录失败");
        }
        return Response.success(result);
    }
}
