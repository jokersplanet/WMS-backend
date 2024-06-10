package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.category.CategoryQueryRequest;
import com.wms.pojo.entity.Category;
import com.wms.mapper.CategoryMapper;
import com.wms.pojo.entity.Typeclass;
import com.wms.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 货物大类 服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public QueryWrapper<Category> getQueryWrapper(CategoryQueryRequest categoryQueryRequest) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if(categoryQueryRequest == null){
            return queryWrapper;
        }
        Integer id = categoryQueryRequest.getCateId();
        String sortField = categoryQueryRequest.getSortField();
        String sortOrder = categoryQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"cate_id",id);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
