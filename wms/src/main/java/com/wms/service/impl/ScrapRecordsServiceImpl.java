package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsQueryRequest;
import com.wms.pojo.entity.OutboundRecords;
import com.wms.pojo.entity.ScrapRecords;
import com.wms.mapper.ScrapRecordsMapper;
import com.wms.service.ScrapRecordsService;
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
public class ScrapRecordsServiceImpl extends ServiceImpl<ScrapRecordsMapper, ScrapRecords> implements ScrapRecordsService {

    @Override
    public QueryWrapper<ScrapRecords> getQueryWrapper(ScrapRecordsQueryRequest scrapRecordsQueryRequest) {
        QueryWrapper<ScrapRecords> queryWrapper = new QueryWrapper<>();
        if(scrapRecordsQueryRequest == null){
            return queryWrapper;
        }
        Integer scrapId = scrapRecordsQueryRequest.getScrapId();
        String time = scrapRecordsQueryRequest.getTime();
        Long count = scrapRecordsQueryRequest.getCount();
        String goodsName = scrapRecordsQueryRequest.getGoodsName();
        Integer wareId = scrapRecordsQueryRequest.getWareId();
        String userAccount = scrapRecordsQueryRequest.getUserAccount();
        BigDecimal price = scrapRecordsQueryRequest.getPrice();
        BigDecimal value = scrapRecordsQueryRequest.getValue();
        String notes = scrapRecordsQueryRequest.getNotes();
        String sortField = scrapRecordsQueryRequest.getSortField();
        String sortOrder = scrapRecordsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(scrapId),"scrap_id",scrapId);
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
