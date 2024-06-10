package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.warehouse.WarehouseQueryRequest;
import com.wms.pojo.entity.Status;
import com.wms.pojo.entity.Warehouse;
import com.wms.mapper.WarehouseMapper;
import com.wms.service.WarehouseService;
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
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Override
    public QueryWrapper<Warehouse> getQueryWrapper(WarehouseQueryRequest warehouseQueryRequest) {
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<>();
        if(warehouseQueryRequest == null){
            return queryWrapper;
        }
        Integer id = warehouseQueryRequest.getWareId();
        String wareHead = warehouseQueryRequest.getWareHead();
        String wareName = warehouseQueryRequest.getWareName();
        BigDecimal wareValue = warehouseQueryRequest.getWareValue();
        String wareNotes = warehouseQueryRequest.getWareNotes();
        String wareAddress = warehouseQueryRequest.getWareAddress();
        String sortField = warehouseQueryRequest.getSortField();
        String sortOrder = warehouseQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"ware_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(wareHead),"ware_head",wareHead);
        queryWrapper.eq(StringUtils.isNotBlank(wareName),"ware_name",wareName);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareValue),"ware_value",wareValue);
        queryWrapper.eq(StringUtils.isNotBlank(wareNotes),"ware_notes",wareNotes);
        queryWrapper.eq(StringUtils.isNotBlank(wareAddress),"ware_address",wareAddress);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
