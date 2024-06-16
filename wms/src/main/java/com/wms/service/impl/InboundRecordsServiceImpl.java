package com.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.inboundRecords.InboundRecordsQueryRequest;
import com.wms.pojo.dto.inboundRecords.InboundRecordsUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.InboundRecords;
import com.wms.mapper.InboundRecordsMapper;
import com.wms.pojo.entity.User;
import com.wms.pojo.entity.Warehouse;
import com.wms.pojo.vo.GoodsVO;
import com.wms.pojo.vo.InboundRecordsVO;
import com.wms.pojo.vo.UserVO;
import com.wms.pojo.vo.WarehouseVO;
import com.wms.service.GoodsService;
import com.wms.service.InboundRecordsService;
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
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-09
 */
@Service
public class InboundRecordsServiceImpl extends ServiceImpl<InboundRecordsMapper, InboundRecords> implements InboundRecordsService {

    @Resource
    private GoodsService goodsService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private UserService userService;
    @Override
    public QueryWrapper<InboundRecords> getQueryWrapper(InboundRecordsQueryRequest inboundRecordsQueryRequest) {
        QueryWrapper<InboundRecords> queryWrapper = new QueryWrapper<>();
        if(inboundRecordsQueryRequest == null){
            return queryWrapper;
        }
        Integer inboundId = inboundRecordsQueryRequest.getInboundId();
        Integer goodsId = inboundRecordsQueryRequest.getGoodsId();
        Integer wareId = inboundRecordsQueryRequest.getWareId();
        String userAccount = inboundRecordsQueryRequest.getUserAccount();
        Date startDate = inboundRecordsQueryRequest.getStartDate();
        Date endDate = inboundRecordsQueryRequest.getEndDate();
        String sortField = inboundRecordsQueryRequest.getSortField();
        String sortOrder = inboundRecordsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(inboundId),"inbound_id",inboundId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(goodsId),"goods_id",goodsId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareId),"ware_id",wareId);
        queryWrapper.eq(StringUtils.isNotBlank(userAccount),"user_account",userAccount);
        queryWrapper.between("time",startDate,endDate);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public InboundRecordsVO getInboundRecordsVO(InboundRecords inboundRecords) {
        InboundRecordsVO inboundRecordsVO = new InboundRecordsVO();
        BeanUtils.copyProperties(inboundRecords,inboundRecordsVO);
        Goods goods = goodsService.getById(inboundRecords.getGoodsId());
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods,goodsVO);
        inboundRecordsVO.setGoodsVO(goodsVO);
        Warehouse warehouse = warehouseService.getById(inboundRecords.getWareId());
        WarehouseVO warehouseVO = new WarehouseVO();
        BeanUtils.copyProperties(warehouse,warehouseVO);
        inboundRecordsVO.setWarehouseVO(warehouseVO);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,inboundRecords.getUserAccount());
        User user = userService.getOne(queryWrapper);
        UserVO userVO =new UserVO();
        BeanUtils.copyProperties(user,userVO);
        inboundRecordsVO.setUserVO(userVO);
        return inboundRecordsVO;
    }

    @Override
    public Page<InboundRecordsVO> getInboundRecordsVOPage(Page<InboundRecords> inboundRecordsPage) {
        List<InboundRecords> recordsList = inboundRecordsPage.getRecords();
        Page<InboundRecordsVO> recordsVOPage = new Page<>(inboundRecordsPage.getCurrent(),inboundRecordsPage.getSize(),inboundRecordsPage.getTotal());
        if(CollUtil.isEmpty(recordsList)){
            return recordsVOPage;
        }
        List<InboundRecordsVO> recordsVOList = recordsList.stream().map(inboundRecords -> {
            return getInboundRecordsVO(inboundRecords);
        }).collect(Collectors.toList());
        recordsVOPage.setRecords(recordsVOList);
        return recordsVOPage;
    }
}
