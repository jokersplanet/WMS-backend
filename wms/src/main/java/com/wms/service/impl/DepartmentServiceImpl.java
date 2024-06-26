package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.department.DepartmentQueryRequest;
import com.wms.pojo.entity.Department;
import com.wms.mapper.DepartmentMapper;
import com.wms.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author wenhui
 * @since 2024-05-28
 */
@Service
@Slf4j
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public QueryWrapper<Department> getQueryWrapper(DepartmentQueryRequest departmentQueryRequest) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        if(departmentQueryRequest == null){
            return queryWrapper;
        }
        Integer id = departmentQueryRequest.getDptId();
        String departmentName = departmentQueryRequest.getDptName();
        String sortField = departmentQueryRequest.getSortField();
        String sortOrder = departmentQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "dpt_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(departmentName), "dpt_name", departmentName);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
