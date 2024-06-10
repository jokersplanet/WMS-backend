package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.goods.GoodsQueryRequest;
import com.wms.pojo.entity.Goods;
import com.wms.mapper.GoodsMapper;
import com.wms.pojo.entity.Unit;
import com.wms.service.GoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public QueryWrapper<Goods> getQueryWrapper(GoodsQueryRequest goodsQueryRequest) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if(goodsQueryRequest == null){
            return queryWrapper;
        }
        Integer id = goodsQueryRequest.getGoodsId();
        String goodsName = goodsQueryRequest.getGoodsName();
        String inboundTime = goodsQueryRequest.getInboundTime();
        String outboundTime = goodsQueryRequest.getOutboundTime();
        Long count = goodsQueryRequest.getCount();
        BigDecimal price = goodsQueryRequest.getPrice();
        BigDecimal value = goodsQueryRequest.getValue();
        Long lowerLimit = goodsQueryRequest.getLowerLimit();
        Long upperLimit = goodsQueryRequest.getUpperLimit();
        Integer classId = goodsQueryRequest.getClassId();
        Integer unitId = goodsQueryRequest.getUnitId();
        Integer cateId = goodsQueryRequest.getCateId();
        Integer wareId = goodsQueryRequest.getWareId();
        String sortField = goodsQueryRequest.getSortField();
        String sortOrder = goodsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"goods_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(goodsName),"goods_name",goodsName);
        queryWrapper.eq(StringUtils.isNotBlank(inboundTime),"inboundTime",inboundTime);
        queryWrapper.eq(StringUtils.isNotBlank(outboundTime),"outboundTime",outboundTime);
        queryWrapper.eq(ObjectUtils.isNotEmpty(count),"count",count);
        queryWrapper.eq(ObjectUtils.isNotEmpty(price),"price",price);
        queryWrapper.eq(ObjectUtils.isNotEmpty(value),"value",value);
        queryWrapper.eq(ObjectUtils.isNotEmpty(lowerLimit),"lowerLimit",lowerLimit);
        queryWrapper.eq(ObjectUtils.isNotEmpty(upperLimit),"upperLimit",upperLimit);
        queryWrapper.eq(ObjectUtils.isNotEmpty(classId),"classId",classId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(unitId),"unitId",unitId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(cateId),"cateId",cateId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareId),"wareId",wareId);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
