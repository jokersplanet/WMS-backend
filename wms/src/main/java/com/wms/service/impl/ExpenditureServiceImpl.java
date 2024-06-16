package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.expenditure.ExpenditureQueryRequest;
import com.wms.pojo.entity.Expenditure;
import com.wms.mapper.ExpenditureMapper;
import com.wms.service.ExpenditureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * <p>
 * 开支 服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-06
 */
@Service
public class ExpenditureServiceImpl extends ServiceImpl<ExpenditureMapper, Expenditure> implements ExpenditureService {

    @Override
    public QueryWrapper<Expenditure> getQueryWrapper(ExpenditureQueryRequest expenditureQueryRequest) {
        QueryWrapper<Expenditure> queryWrapper = new QueryWrapper<>();
        if(expenditureQueryRequest == null){
            return queryWrapper;
        }
        Integer expId = expenditureQueryRequest.getExpId();
        Date startDate = expenditureQueryRequest.getStartDate();
        Date endDate = expenditureQueryRequest.getEndDate();
        BigDecimal value = expenditureQueryRequest.getValue();
        String destination = expenditureQueryRequest.getDestination();
        String notes = expenditureQueryRequest.getNotes();
        String sortField = expenditureQueryRequest.getSortField();
        String sortOrder = expenditureQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(expId),"exp_id",expId);
        queryWrapper.between("time",startDate,endDate);
        queryWrapper.eq(ObjectUtils.isNotEmpty(value),"value",value);
        queryWrapper.eq(StringUtils.isNotBlank(destination),"destination",destination);
        queryWrapper.eq(StringUtils.isNotBlank(notes),"notes",notes);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
