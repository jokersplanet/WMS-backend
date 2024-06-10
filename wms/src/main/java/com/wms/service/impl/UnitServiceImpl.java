package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.unit.UnitQueryRequest;
import com.wms.pojo.entity.Typeclass;
import com.wms.pojo.entity.Unit;
import com.wms.mapper.UnitMapper;
import com.wms.service.UnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {

    @Override
    public QueryWrapper<Unit> getQueryWrapper(UnitQueryRequest unitQueryRequest) {
        QueryWrapper<Unit> queryWrapper = new QueryWrapper<>();
        if(unitQueryRequest == null){
            return queryWrapper;
        }
        Integer id = unitQueryRequest.getUnitId();
        String unitName = unitQueryRequest.getUnitName();
        String sortField = unitQueryRequest.getSortField();
        String sortOrder = unitQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"unit_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(unitName),"unit_name",unitName);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
