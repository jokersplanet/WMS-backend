package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.typeclass.TypeclassQueryRequest;
import com.wms.pojo.entity.Typeclass;
import com.wms.mapper.TypeclassMapper;
import com.wms.pojo.entity.Warehouse;
import com.wms.service.TypeclassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 货物种类 服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Service
public class TypeclassServiceImpl extends ServiceImpl<TypeclassMapper, Typeclass> implements TypeclassService {

    @Override
    public QueryWrapper<Typeclass> getQueryWrapper(TypeclassQueryRequest typeclassQueryRequest) {
        QueryWrapper<Typeclass> queryWrapper = new QueryWrapper<>();
        if(typeclassQueryRequest == null){
            return queryWrapper;
        }
        Integer id = typeclassQueryRequest.getClassId();
        String className = typeclassQueryRequest.getClassName();
        Integer categoryId = typeclassQueryRequest.getCategoryId();
        String sortField = typeclassQueryRequest.getSortField();
        String sortOrder = typeclassQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"class_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(className),"class_name",className);
        queryWrapper.eq(ObjectUtils.isNotEmpty(categoryId),"category_id",categoryId);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
