package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.status.StatusAddRequest;
import com.wms.pojo.dto.status.StatusDeleteRequest;
import com.wms.pojo.dto.status.StatusQueryRequest;
import com.wms.pojo.dto.status.StatusUpdateRequest;
import com.wms.pojo.entity.Orders;
import com.wms.pojo.entity.Status;
import com.wms.service.OrdersService;
import com.wms.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author lwh
 * @since 2024-06-07
 */
@RestController
@RequestMapping("/status")
@Slf4j
public class StatusController {

    @Resource
    private StatusService statusService;
    @Resource
    private OrdersService ordersService;

    /**
     * 分页获取状态信息
     * @param statusQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('status.listPage')")
    public Response<Page<Status>> listStatusByPage(@RequestBody StatusQueryRequest statusQueryRequest){
        int current = statusQueryRequest.getCurrent();
        int size = statusQueryRequest.getPageSize();
        Page<Status> page = statusService.page(new Page<>(current, size), statusService.getQueryWrapper(statusQueryRequest));
        return Response.success(page);
    }

    /**
     * 增加状态记录
     * @param statusAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('status.add')")
    public Response<Integer> statusAdd(@RequestBody StatusAddRequest statusAddRequest){
        if(statusAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Status status = new Status();
        BeanUtils.copyProperties(statusAddRequest,status);
        boolean save = statusService.save(status);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return Response.success(status.getId());
    }

    /**
     * 删除状态记录
     * @param statusDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('status.delete')")
    public Response<Boolean> statusDelete(@RequestBody StatusDeleteRequest statusDeleteRequest){
        if(statusDeleteRequest == null || statusDeleteRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getStatus,statusDeleteRequest.getId());
        if(ordersService.getOne(queryWrapper,false) != null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"该状态下存在订单无法删除");
        }else{
            boolean result = statusService.removeById(statusDeleteRequest.getId());
            return Response.success(result);
        }
    }

    /**
     * 更新状态记录
     * @param statusUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('status.update')")
    public Response<Boolean> statusUpdate(@RequestBody StatusUpdateRequest statusUpdateRequest){
        if(statusUpdateRequest == null || statusUpdateRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Status status = new Status();
        BeanUtils.copyProperties(statusUpdateRequest,status);
        if(statusService.getById(statusUpdateRequest.getId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该状态，无法更新该状态信息");
        }else{
            boolean result = statusService.updateById(status);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改状态记录失败");
            }
            return Response.success(result);
        }
    }
}
