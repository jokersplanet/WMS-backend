package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.status.StatusQueryRequest;
import com.wms.pojo.entity.Expenditure;
import com.wms.pojo.entity.Status;
import com.wms.mapper.StatusMapper;
import com.wms.service.StatusService;
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
 * @since 2024-06-07
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {

    @Override
    public QueryWrapper<Status> getQueryWrapper(StatusQueryRequest statusQueryRequest) {
        QueryWrapper<Status> queryWrapper = new QueryWrapper<>();
        if(statusQueryRequest == null){
            return queryWrapper;
        }
        Integer id = statusQueryRequest.getId();
        String name = statusQueryRequest.getName();
        String sequence = statusQueryRequest.getSequence();
        String sortField = statusQueryRequest.getSortField();
        String sortOrder = statusQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"id",id);
        queryWrapper.eq(StringUtils.isNotBlank(name),"name",name);
        queryWrapper.eq(StringUtils.isNotBlank(sequence),"sequence",sequence);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
