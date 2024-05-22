package com.wms.service.impl;

import com.wms.model.entity.User;
import com.wms.mapper.UserMapper;
import com.wms.service.UserServer;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenhui
 * @since 2024-05-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserServer {

}
