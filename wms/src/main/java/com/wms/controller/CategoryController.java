package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.category.CategoryAddRequest;
import com.wms.pojo.dto.category.CategoryDeleteRequest;
import com.wms.pojo.dto.category.CategoryQueryRequest;
import com.wms.pojo.dto.category.CategoryUpdateRequest;
import com.wms.pojo.dto.status.StatusUpdateRequest;
import com.wms.pojo.dto.typeclass.TypeclassAddRequest;
import com.wms.pojo.dto.typeclass.TypeclassDeleteRequest;
import com.wms.pojo.dto.typeclass.TypeclassQueryRequest;
import com.wms.pojo.entity.Category;
import com.wms.pojo.entity.Orders;
import com.wms.pojo.entity.Status;
import com.wms.pojo.entity.Typeclass;
import com.wms.service.CategoryService;
import com.wms.service.TypeclassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 货物大类 前端控制器
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private TypeclassService typeclassService;

    /**
     * 分页获取大类信息
     * @param categoryQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Category>> listCategoryByPage(@RequestBody CategoryQueryRequest categoryQueryRequest){
        int current = categoryQueryRequest.getCurrent();
        int size = categoryQueryRequest.getPageSize();
        Page<Category> page = categoryService.page(new Page<>(current, size), categoryService.getQueryWrapper(categoryQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加大类记录
     * @param categoryAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> categoryAdd(@RequestBody CategoryAddRequest categoryAddRequest){
        if(categoryAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddRequest,category);
        boolean save = categoryService.save(category);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultsSet.success(category.getCateId());
    }

    /**
     * 删除大类记录
     * @param categoryDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> categoryDelete(@RequestBody CategoryDeleteRequest categoryDeleteRequest){
        if(categoryDeleteRequest == null || categoryDeleteRequest.getCateId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        LambdaQueryWrapper<Typeclass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Typeclass::getCategoryId,categoryDeleteRequest.getCateId());
        if(typeclassService.getOne(queryWrapper,false) != null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"该大类下存在小类无法删除");
        }else{
            boolean result = categoryService.removeById(categoryDeleteRequest.getCateId());
            return ResultsSet.success(result);
        }
    }

    /**
     * 更新大类记录
     * @param categoryUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> categoryUpdate(@RequestBody CategoryUpdateRequest categoryUpdateRequest){
        if(categoryUpdateRequest == null || categoryUpdateRequest.getCateId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateRequest,category);
        boolean result = categoryService.updateById(category);
        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改大类记录失败");
        }
        return ResultsSet.success(result);
    }
}
