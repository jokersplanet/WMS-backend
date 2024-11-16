package com.jiaotangbing.wms.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiaotangbing.wms.common.domain.dos.UserRoleDO;

import java.util.List;

/**
 * @author lwh
 * @create 2024-11-13 16:03
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    default List<UserRoleDO> selectByUsername(String username) {
        LambdaQueryWrapper<UserRoleDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoleDO::getUsername, username);

        return selectList(wrapper);
    }
}
