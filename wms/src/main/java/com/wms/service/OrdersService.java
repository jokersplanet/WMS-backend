package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.orders.OrdersQueryRequest;
import com.wms.pojo.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
public interface OrdersService extends IService<Orders> {

    QueryWrapper<Orders> getQueryWrapper(OrdersQueryRequest ordersQueryRequest);
}
