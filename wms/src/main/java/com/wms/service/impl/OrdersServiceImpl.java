package com.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.orders.OrdersQueryRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.Orders;
import com.wms.mapper.OrdersMapper;
import com.wms.pojo.entity.Status;
import com.wms.pojo.vo.GoodsVO;
import com.wms.pojo.vo.OrdersVO;
import com.wms.pojo.vo.StatusVO;
import com.wms.service.GoodsService;
import com.wms.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.service.StatusService;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private GoodsService goodsService;
    @Resource
    private StatusService statusService;

    @Override
    public QueryWrapper<Orders> getQueryWrapper(OrdersQueryRequest ordersQueryRequest) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if(ordersQueryRequest == null){
            return queryWrapper;
        }
        Integer id = ordersQueryRequest.getOrderId();
        Integer goodsId = ordersQueryRequest.getGoodsId();
        Integer status = ordersQueryRequest.getStatus();
        Date startDate = ordersQueryRequest.getStartDate();
        Date endDate = ordersQueryRequest.getEndDate();
        String sortField = ordersQueryRequest.getSortField();
        String sortOrder = ordersQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"order_id",id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(goodsId),"goods_id",goodsId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status),"status",status);
        queryWrapper.between("time",startDate,endDate);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public OrdersVO getOrdersVO(Orders orders) {
        OrdersVO ordersVO = new OrdersVO();
        BeanUtils.copyProperties(orders,ordersVO);
        Goods goods = goodsService.getById(orders.getGoodsId());
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods, goodsVO);
        ordersVO.setGoodsVO(goodsVO);
        Status status = statusService.getById(orders.getStatus());
        StatusVO statusVO = new StatusVO();
        BeanUtils.copyProperties(status,statusVO);
        ordersVO.setStatusVO(statusVO);
        return ordersVO;
    }

    @Override
    public Page<OrdersVO> getOrdersVOPage(Page<Orders> ordersPage) {
        List<Orders> ordersList = ordersPage.getRecords();
        Page<OrdersVO> ordersVOPage = new Page<>(ordersPage.getCurrent(),ordersPage.getSize(),ordersPage.getTotal());
        if(CollUtil.isEmpty(ordersList)){
            return ordersVOPage;
        }
        List<OrdersVO> ordersVOList = ordersList.stream().map(orders -> {
            return getOrdersVO(orders);
        }).collect(Collectors.toList());
        ordersVOPage.setRecords(ordersVOList);
        return ordersVOPage;
    }
}
