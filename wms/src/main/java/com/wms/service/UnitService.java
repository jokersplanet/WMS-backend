package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.unit.UnitQueryRequest;
import com.wms.pojo.entity.Unit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
public interface UnitService extends IService<Unit> {

    QueryWrapper<Unit> getQueryWrapper(UnitQueryRequest unitQueryRequest);
}
