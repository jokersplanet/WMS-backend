package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.warehouse.WarehouseAddRequest;
import com.wms.pojo.dto.warehouse.WarehouseDeleteRequest;
import com.wms.pojo.dto.warehouse.WarehouseQueryRequest;
import com.wms.pojo.dto.warehouse.WarehouseUpdateRequest;
import com.wms.pojo.entity.Warehouse;
import com.wms.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/warehouse")
@Slf4j
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    /**
     * 分页获取仓库信息
     * @param warehouseQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('warehouse.listPage')")
    public Response<Page<Warehouse>> listWarehouseByPage(@RequestBody WarehouseQueryRequest warehouseQueryRequest){
        int current = warehouseQueryRequest.getCurrent();
        int size = warehouseQueryRequest.getPageSize();
        Page<Warehouse> page = warehouseService.page(new Page<>(current, size), warehouseService.getQueryWrapper(warehouseQueryRequest));
        return Response.success(page);
    }

    /**
     * 增加仓库记录
     * @param warehouseAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('warehouse.add')")
    public Response<Integer> warehouseAdd(@RequestBody WarehouseAddRequest warehouseAddRequest){
        if(warehouseAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehouseAddRequest,warehouse);
        boolean save = warehouseService.save(warehouse);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return Response.success(warehouse.getWareId());
    }

    /**
     * 删除仓库记录
     * @param warehouseDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('warehouse.delete')")
    public Response<Boolean> warehouseDelete(@RequestBody WarehouseDeleteRequest warehouseDeleteRequest){
        if(warehouseDeleteRequest == null || warehouseDeleteRequest.getWareId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = warehouseService.removeById(warehouseDeleteRequest.getWareId());
        return Response.success(result);
    }

    /**
     * 修改仓库信息
     * @param warehouseUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('warehouse.update')")
    public Response<Boolean> warehouseUpdate(@RequestBody WarehouseUpdateRequest warehouseUpdateRequest){
        if(warehouseUpdateRequest == null || warehouseUpdateRequest.getWareId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehouseUpdateRequest,warehouse);
        if(warehouseService.getById(warehouseUpdateRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，无法修改该仓库信息");
        }else{
            boolean result = warehouseService.updateById(warehouse);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改状态记录失败");
            }
            return Response.success(result);
        }
    }
}
