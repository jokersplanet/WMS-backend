package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.expenditure.ExpenditureQueryRequest;
import com.wms.pojo.entity.Expenditure;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 开支 服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-06
 */
public interface ExpenditureService extends IService<Expenditure> {

    QueryWrapper<Expenditure> getQueryWrapper(ExpenditureQueryRequest expenditureQueryRequest);
}
