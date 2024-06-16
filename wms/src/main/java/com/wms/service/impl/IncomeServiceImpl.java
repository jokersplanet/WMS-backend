package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.income.IncomeQueryRequest;
import com.wms.pojo.entity.Income;
import com.wms.mapper.IncomeMapper;
import com.wms.service.IncomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-06
 */
@Service
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IncomeService {

    @Override
    public QueryWrapper<Income> getQueryWrapper(IncomeQueryRequest incomeQueryRequest) {
        QueryWrapper<Income> queryWrapper = new QueryWrapper<>();
        if(incomeQueryRequest == null){
            return queryWrapper;
        }
        Integer incomeId = incomeQueryRequest.getIncomeId();
        Date startDate = incomeQueryRequest.getStartDate();
        Date endDate = incomeQueryRequest.getEndDate();
        BigDecimal value = incomeQueryRequest.getValue();
        String origin = incomeQueryRequest.getOrigin();
        String notes = incomeQueryRequest.getNotes();
        String sortField = incomeQueryRequest.getSortField();
        String sortOrder = incomeQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(incomeId),"income_id",incomeId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(value),"value",value);
        queryWrapper.eq(StringUtils.isNotBlank(origin),"origin",origin);
        queryWrapper.eq(StringUtils.isNotBlank(notes),"notes",notes);
        queryWrapper.between("time",startDate,endDate);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),sortOrder.equals(CommonConstant.SORT_ORDER_ASC),sortField);
        return queryWrapper;
    }
}
