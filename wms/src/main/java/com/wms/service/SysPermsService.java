package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.sysPerms.SysPermsQueryRequest;
import com.wms.pojo.entity.SysPerms;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.management.Query;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-25
 */
public interface SysPermsService extends IService<SysPerms> {

    QueryWrapper<SysPerms> getQueryWrapper(SysPermsQueryRequest sysPermsQueryRequest);

    List<String> findUserPermsByUserId(Long userId);
}
