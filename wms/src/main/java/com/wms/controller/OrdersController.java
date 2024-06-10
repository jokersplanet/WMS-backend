package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.orders.OrdersAddRequest;
import com.wms.pojo.dto.orders.OrdersDeleteRequest;
import com.wms.pojo.dto.orders.OrdersQueryRequest;
import com.wms.pojo.dto.orders.OrdersUpdateRequest;
import com.wms.pojo.entity.Orders;
import com.wms.service.OrdersService;
import com.wms.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/orders")
@Slf4j
public class OrdersController {

    @Resource
    private OrdersService ordersService;
    @Resource
    private StatusService statusService;

    /**
     * 分页获取订单信息
     * @param ordersQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Orders>> listOrdersByPage(@RequestBody OrdersQueryRequest ordersQueryRequest){
        int current = ordersQueryRequest.getCurrent();
        int size = ordersQueryRequest.getPageSize();
        Page<Orders> page = ordersService.page(new Page<>(current, size), ordersService.getQueryWrapper(ordersQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加订单记录
     * @param ordersAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> ordersAdd(@RequestBody OrdersAddRequest ordersAddRequest){
        if(ordersAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Orders order = new Orders();
        BeanUtils.copyProperties(ordersAddRequest,order);
        if(statusService.getById(ordersAddRequest.getStatus()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该状态信息");
        }else{
            boolean save = ordersService.save(order);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return ResultsSet.success(order.getOrderId());
        }
    }

    /**
     * 删除订单记录
     * @param ordersDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> ordersDelete(@RequestBody OrdersDeleteRequest ordersDeleteRequest){
        if(ordersDeleteRequest == null || ordersDeleteRequest.getOrderId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = ordersService.removeById(ordersDeleteRequest.getOrderId());
        return ResultsSet.success(result);
    }

    /**
     * 修改订单信息
     * @param ordersUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> ordersUpdate(@RequestBody OrdersUpdateRequest ordersUpdateRequest){
        if(ordersUpdateRequest == null || ordersUpdateRequest.getOrderId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Orders order = new Orders();
        BeanUtils.copyProperties(ordersUpdateRequest,order);
        if(statusService.getById(ordersUpdateRequest.getStatus()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该状态信息");
        }else{
            boolean result = ordersService.updateById(order);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改订单失败");
            }
            return ResultsSet.success(result);
        }
    }
}
