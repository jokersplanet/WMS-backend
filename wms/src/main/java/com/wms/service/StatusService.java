package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.status.StatusQueryRequest;
import com.wms.pojo.entity.Status;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-07
 */
public interface StatusService extends IService<Status> {

    QueryWrapper<Status> getQueryWrapper(StatusQueryRequest statusQueryRequest);
}
