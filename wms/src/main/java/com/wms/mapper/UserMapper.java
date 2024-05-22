package com.wms.mapper;

import com.wms.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wenhui
 * @since 2024-05-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
