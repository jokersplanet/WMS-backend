package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.orders.OrdersQueryRequest;
import com.wms.pojo.entity.Orders;
import com.wms.mapper.OrdersMapper;
import com.wms.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public QueryWrapper<Orders> getQueryWrapper(OrdersQueryRequest ordersQueryRequest) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if(ordersQueryRequest == null){
            return queryWrapper;
        }
        Integer id = ordersQueryRequest.getOrderId();
        String goodsName = ordersQueryRequest.getGoodsName();
        BigDecimal price = ordersQueryRequest.getPrice();
        BigDecimal value = ordersQueryRequest.getValue();
        Integer count = ordersQueryRequest.getCount();
        String time = ordersQueryRequest.getTime();
        Integer status = ordersQueryRequest.getStatus();
        String notes = ordersQueryRequest.getNotes();
        String sortField = ordersQueryRequest.getSortField();
        String sortOrder = ordersQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"order_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(goodsName),"goods_name",goodsName);
        queryWrapper.eq(ObjectUtils.isNotEmpty(price),"price",price);
        queryWrapper.eq(ObjectUtils.isNotEmpty(value),"value",value);
        queryWrapper.eq(ObjectUtils.isNotEmpty(count),"count",count);
        queryWrapper.eq(StringUtils.isNotBlank(time),"time",time);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status),"status",status);
        queryWrapper.eq(StringUtils.isNotBlank(notes),"notes",notes);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
