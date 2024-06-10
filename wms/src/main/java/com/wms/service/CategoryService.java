package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.category.CategoryQueryRequest;
import com.wms.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 货物大类 服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
public interface CategoryService extends IService<Category> {

    QueryWrapper<Category> getQueryWrapper(CategoryQueryRequest categoryQueryRequest);
}
