package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsQueryRequest;
import com.wms.pojo.entity.InboundRecords;
import com.wms.pojo.entity.OutboundRecords;
import com.wms.mapper.OutboundRecordsMapper;
import com.wms.service.OutboundRecordsService;
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
 * @since 2024-06-09
 */
@Service
public class OutboundRecordsServiceImpl extends ServiceImpl<OutboundRecordsMapper, OutboundRecords> implements OutboundRecordsService {

    @Override
    public QueryWrapper<OutboundRecords> getQueryWrapper(OutboundRecordsQueryRequest outboundRecordsQueryRequest) {
        QueryWrapper<OutboundRecords> queryWrapper = new QueryWrapper<>();
        if(outboundRecordsQueryRequest == null){
            return queryWrapper;
        }
        Integer outboundId = outboundRecordsQueryRequest.getOutboundId();
        String time = outboundRecordsQueryRequest.getTime();
        Integer count = outboundRecordsQueryRequest.getCount();
        String goodsName = outboundRecordsQueryRequest.getGoodsName();
        Integer wareId = outboundRecordsQueryRequest.getWareId();
        String userAccount = outboundRecordsQueryRequest.getUserAccount();
        BigDecimal price = outboundRecordsQueryRequest.getPrice();
        BigDecimal value = outboundRecordsQueryRequest.getValue();
        String notes = outboundRecordsQueryRequest.getNotes();
        String sortField = outboundRecordsQueryRequest.getSortField();
        String sortOrder = outboundRecordsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(outboundId),"outbound_id",outboundId);
        queryWrapper.eq(StringUtils.isNotBlank(time),"time",time);
        queryWrapper.eq(ObjectUtils.isNotEmpty(count),"count",count);
        queryWrapper.eq(StringUtils.isNotBlank(goodsName),"goods_name",goodsName);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareId),"ware_id",wareId);
        queryWrapper.eq(StringUtils.isNotBlank(userAccount),"user_account",userAccount);
        queryWrapper.eq(ObjectUtils.isNotEmpty(price),"price",price);
        queryWrapper.eq(ObjectUtils.isNotEmpty(value),"value",value);
        queryWrapper.eq(StringUtils.isNotBlank(notes),"notes",notes);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
