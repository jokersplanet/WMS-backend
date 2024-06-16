package com.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsQueryRequest;
import com.wms.pojo.entity.*;
import com.wms.mapper.OutboundRecordsMapper;
import com.wms.pojo.vo.*;
import com.wms.service.GoodsService;
import com.wms.service.OutboundRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.service.UserService;
import com.wms.service.WarehouseService;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-09
 */
@Service
public class OutboundRecordsServiceImpl extends ServiceImpl<OutboundRecordsMapper, OutboundRecords> implements OutboundRecordsService {

    @Resource
    private GoodsService goodsService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private UserService userService;

    @Override
    public QueryWrapper<OutboundRecords> getQueryWrapper(OutboundRecordsQueryRequest outboundRecordsQueryRequest) {
        QueryWrapper<OutboundRecords> queryWrapper = new QueryWrapper<>();
        if (outboundRecordsQueryRequest == null) {
            return queryWrapper;
        }
        Integer outboundId = outboundRecordsQueryRequest.getOutboundId();
        Integer goodsId = outboundRecordsQueryRequest.getGoodsId();
        Integer wareId = outboundRecordsQueryRequest.getWareId();
        String userAccount = outboundRecordsQueryRequest.getUserAccount();
        String sortField = outboundRecordsQueryRequest.getSortField();
        String sortOrder = outboundRecordsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(outboundId), "outbound_id", outboundId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(goodsId),"goods_id",goodsId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareId), "ware_id", wareId);
        queryWrapper.eq(StringUtils.isNotBlank(userAccount), "user_account", userAccount);
        queryWrapper.eq("is_deleted", true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public OutboundRecordsVO getOutboundRecordsVO(OutboundRecords outboundRecords) {
        OutboundRecordsVO outboundRecordsVO = new OutboundRecordsVO();
        BeanUtils.copyProperties(outboundRecords, outboundRecordsVO);
        Goods goods = goodsService.getById(outboundRecords.getGoodsId());
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods, goodsVO);
        outboundRecordsVO.setGoodsVO(goodsVO);
        Warehouse warehouse = warehouseService.getById(outboundRecords.getWareId());
        WarehouseVO warehouseVO = new WarehouseVO();
        BeanUtils.copyProperties(warehouse,warehouseVO);
        outboundRecordsVO.setWarehouseVO(warehouseVO);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,outboundRecords.getUserAccount());
        User user = userService.getOne(queryWrapper);
        UserVO userVO =new UserVO();
        BeanUtils.copyProperties(user,userVO);
        outboundRecordsVO.setUserVO(userVO);
        return outboundRecordsVO;
    }

    @Override
    public Page<OutboundRecordsVO> getOutboundRecordsVOPage(Page<OutboundRecords> outboundRecordsPage) {
        List<OutboundRecords> recordsList = outboundRecordsPage.getRecords();
        Page<OutboundRecordsVO> recordsVOPage = new Page<>(outboundRecordsPage.getCurrent(),outboundRecordsPage.getSize(),outboundRecordsPage.getTotal());
        if(CollUtil.isEmpty(recordsList)){
            return recordsVOPage;
        }
        List<OutboundRecordsVO> recordsVOList = recordsList.stream().map(outboundRecords -> {
            return getOutboundRecordsVO(outboundRecords);
        }).collect(Collectors.toList());
        recordsVOPage.setRecords(recordsVOList);
        return recordsVOPage;
    }
}
