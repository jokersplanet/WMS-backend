package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.inboundRecords.InboundRecordsQueryRequest;
import com.wms.pojo.entity.InboundRecords;
import com.wms.mapper.InboundRecordsMapper;
import com.wms.service.InboundRecordsService;
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
public class InboundRecordsServiceImpl extends ServiceImpl<InboundRecordsMapper, InboundRecords> implements InboundRecordsService {

    @Override
    public QueryWrapper<InboundRecords> getQueryWrapper(InboundRecordsQueryRequest inboundRecordsQueryRequest) {
        QueryWrapper<InboundRecords> queryWrapper = new QueryWrapper<>();
        if(inboundRecordsQueryRequest == null){
            return queryWrapper;
        }
        Integer inboundId = inboundRecordsQueryRequest.getInboundId();
        String time = inboundRecordsQueryRequest.getTime();
        Long count = inboundRecordsQueryRequest.getCount();
        String goodsName = inboundRecordsQueryRequest.getGoodsName();
        Integer wareId = inboundRecordsQueryRequest.getWareId();
        String userAccount = inboundRecordsQueryRequest.getUserAccount();
        BigDecimal price = inboundRecordsQueryRequest.getPrice();
        BigDecimal value = inboundRecordsQueryRequest.getValue();
        String notes = inboundRecordsQueryRequest.getNotes();
        String sortField = inboundRecordsQueryRequest.getSortField();
        String sortOrder = inboundRecordsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(inboundId),"inbound_id",inboundId);
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
