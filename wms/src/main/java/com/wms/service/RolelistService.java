package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wms.pojo.dto.rolelist.RolelistdoAssignRequest;
import com.wms.pojo.dto.rolelist.RolelistQueryRequest;
import com.wms.pojo.entity.Rolelist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-16
 */
public interface RolelistService extends IService<Rolelist> {

    Wrapper<Rolelist> getQueryWrapper(RolelistQueryRequest rolelistQueryRequest);
    

    void doAssign(RolelistdoAssignRequest rolelistAssignRequest);

    Map<String, Object> findRoleDataByUserId(Integer userId);
}
