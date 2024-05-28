package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.department.DepartmentQueryRequest;
import com.wms.pojo.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wenhui
 * @since 2024-05-28
 */
public interface DepartmentService extends IService<Department> {

    QueryWrapper<Department> getQueryWrapper(DepartmentQueryRequest departmentQueryRequest);
}
