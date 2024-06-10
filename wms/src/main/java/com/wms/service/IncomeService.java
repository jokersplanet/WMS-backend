package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.income.IncomeQueryRequest;
import com.wms.pojo.entity.Income;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-06
 */
public interface IncomeService extends IService<Income> {

    QueryWrapper<Income> getQueryWrapper(IncomeQueryRequest incomeQueryRequest);
}
