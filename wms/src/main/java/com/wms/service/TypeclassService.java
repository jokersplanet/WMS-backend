package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.typeclass.TypeclassQueryRequest;
import com.wms.pojo.entity.Typeclass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 货物种类 服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
public interface TypeclassService extends IService<Typeclass> {

    QueryWrapper<Typeclass> getQueryWrapper(TypeclassQueryRequest typeclassQueryRequest);
}
