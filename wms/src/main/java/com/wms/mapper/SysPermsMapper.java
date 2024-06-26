package com.wms.mapper;

import com.wms.pojo.entity.SysPerms;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwh
 * @since 2024-06-25
 */
public interface SysPermsMapper extends BaseMapper<SysPerms> {

    List<String> findPermsListByUserId(@Param("userId") Long userId);
}
