package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.department.DepartmentAddRequest;
import com.wms.pojo.dto.department.DepartmentDeleteRequest;
import com.wms.pojo.dto.department.DepartmentQueryRequest;
import com.wms.pojo.dto.department.DepartmentUpdateRequest;
import com.wms.pojo.entity.Department;
import com.wms.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author wenhui
 * @since 2024-05-28
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    /**
     * 分页获取部门列表
     * @param departmentQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Department>> listDepartmentByPage(@RequestBody DepartmentQueryRequest departmentQueryRequest){
        int current = departmentQueryRequest.getCurrent();
        int size = departmentQueryRequest.getPageSize();
        Page<Department> page = departmentService.page(new Page<>(current, size), departmentService.getQueryWrapper(departmentQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 新增部门
     * @param departmentAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> departmentAdd(@RequestBody DepartmentAddRequest departmentAddRequest) {
        if (departmentAddRequest == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Department department = new Department();
        DepartmentQueryRequest departmentQueryRequest = new DepartmentQueryRequest();
        BeanUtils.copyProperties(departmentAddRequest, department);
        BeanUtils.copyProperties(department, departmentQueryRequest);
        int result = departmentService.count(departmentService.getQueryWrapper(departmentQueryRequest));
        if (result > 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"该部门已存在");
        }
        boolean save = departmentService.save(department);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultsSet.success(department.getDptId());
    }

    /**
     * 删除部门
     * @param departmentDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> departmentDelete(@RequestBody DepartmentDeleteRequest departmentDeleteRequest){
        if(departmentDeleteRequest == null || departmentDeleteRequest.getDptId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = departmentService.removeById(departmentDeleteRequest.getDptId());
        return ResultsSet.success(result);
    }

    /**
     * 更新部门
     * @param departmentUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> departmentUpdate(@RequestBody DepartmentUpdateRequest departmentUpdateRequest){
        if(departmentUpdateRequest == null || departmentUpdateRequest.getDptId() == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Department department = new Department();
        DepartmentQueryRequest departmentQueryRequest = new DepartmentQueryRequest();
        BeanUtils.copyProperties(departmentUpdateRequest,department);
        BeanUtils.copyProperties(department,departmentQueryRequest);
        int count = departmentService.count(departmentService.getQueryWrapper(departmentQueryRequest));
        if(count > 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"该部门已存在");
        }
        boolean result = departmentService.updateById(department);
        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultsSet.success(result);
    }
}
