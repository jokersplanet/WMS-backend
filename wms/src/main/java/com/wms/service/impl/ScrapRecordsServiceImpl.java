package com.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsQueryRequest;
import com.wms.pojo.entity.*;
import com.wms.mapper.ScrapRecordsMapper;
import com.wms.pojo.vo.*;
import com.wms.service.GoodsService;
import com.wms.service.ScrapRecordsService;
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
public class ScrapRecordsServiceImpl extends ServiceImpl<ScrapRecordsMapper, ScrapRecords> implements ScrapRecordsService {

    @Resource
    private GoodsService goodsService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private UserService userService;

    @Override
    public QueryWrapper<ScrapRecords> getQueryWrapper(ScrapRecordsQueryRequest scrapRecordsQueryRequest) {
        QueryWrapper<ScrapRecords> queryWrapper = new QueryWrapper<>();
        if(scrapRecordsQueryRequest == null){
            return queryWrapper;
        }
        Integer scrapId = scrapRecordsQueryRequest.getScrapId();
        Integer goodsId = scrapRecordsQueryRequest.getGoodsId();
        Integer wareId = scrapRecordsQueryRequest.getWareId();
        String userAccount = scrapRecordsQueryRequest.getUserAccount();
        Date startDate = scrapRecordsQueryRequest.getStartDate();
        Date endDate = scrapRecordsQueryRequest.getEndDate();
        String sortField = scrapRecordsQueryRequest.getSortField();
        String sortOrder = scrapRecordsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(scrapId),"scrap_id",scrapId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(goodsId),"goods_id",goodsId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareId),"ware_id",wareId);
        queryWrapper.eq(StringUtils.isNotBlank(userAccount),"user_account",userAccount);
        queryWrapper.between("time",startDate,endDate);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public ScrapRecordsVO getScrapRecordsVO(ScrapRecords scrapRecords) {
        ScrapRecordsVO scrapRecordsVO = new ScrapRecordsVO();
        BeanUtils.copyProperties(scrapRecords,scrapRecordsVO);
        Goods goods = goodsService.getById(scrapRecords.getGoodsId());
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods, goodsVO);
        scrapRecordsVO.setGoodsVO(goodsVO);
        Warehouse warehouse = warehouseService.getById(scrapRecords.getWareId());
        WarehouseVO warehouseVO = new WarehouseVO();
        BeanUtils.copyProperties(warehouse,warehouseVO);
        scrapRecordsVO.setWarehouseVO(warehouseVO);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,scrapRecords.getUserAccount());
        User user = userService.getOne(queryWrapper);
        UserVO userVO =new UserVO();
        BeanUtils.copyProperties(user,userVO);
        scrapRecordsVO.setUserVO(userVO);
        return scrapRecordsVO;
    }

    @Override
    public Page<ScrapRecordsVO> getScrapRecordsVOPage(Page<ScrapRecords> scrapRecordsPage) {
        List<ScrapRecords> recordsList = scrapRecordsPage.getRecords();
        Page<ScrapRecordsVO> recordsVOPage = new Page<>(scrapRecordsPage.getCurrent(),scrapRecordsPage.getSize(),scrapRecordsPage.getTotal());
        if(CollUtil.isEmpty(recordsList)){
            return recordsVOPage;
        }
        List<ScrapRecordsVO> recordsVOList = recordsList.stream().map(scrapRecords -> {
            return getScrapRecordsVO(scrapRecords);
        }).collect(Collectors.toList());
        recordsVOPage.setRecords(recordsVOList);
        return recordsVOPage;
    }
}
