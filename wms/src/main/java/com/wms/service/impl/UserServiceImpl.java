package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.common.ErrorCode;
import com.wms.constant.CommonConstant;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.user.UserQueryRequest;
import com.wms.pojo.entity.User;
import com.wms.mapper.UserMapper;
import com.wms.pojo.vo.UserLoginVO;
import com.wms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import static com.wms.constant.UserConstant.USER_LOGIN_STATE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wenhui
 * @since 2024-05-20
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    /**
     * 加盐算法：盐值
     */
    public static final String SALT = "wenhui";

    /**
     * 用户注册
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //注册校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "用户密码不能少于8位");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "两次输入的密码不一致");
        }
        synchronized (userAccount.intern()) {
            //防止账户重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_account", userAccount);
            long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.REQUEST_ERROR, "账号重复");
            }
            //md5加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            //用户表中插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveresult = this.save(user);
            if (!saveresult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册错误，插入数据失败");
            }
            return user.getUid();
        }
    }

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    @Override
    public UserLoginVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "账号密码不能为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "请输入完整账号");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "请输入完整密码");
        }
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount);
        queryWrapper.eq("user_password",encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        //如果用户不存在
        if(user == null){
            log.info("user failed to log in, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"用户不存在或者密码输入错误");
        }
        //将用户状态记录为登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,user);
        return this.getUserLoginVO(user);
    }

    @Override
    public UserLoginVO getUserLoginVO(User user){
        if(user == null){
            return null;
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user,userLoginVO);
        return userLoginVO;
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    public boolean userLogout(HttpServletRequest request){
        //如果未曾登录
        if(request.getSession().getAttribute(USER_LOGIN_STATE) == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"未登录");
        }
        //移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        //先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null || currentUser.getUid() == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        //从数据库中查询当前用户
        Integer userUid = currentUser.getUid();
        currentUser = this.getById(userUid);
        if(currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "请求参数为空");
        }
        Integer uid = userQueryRequest.getUid();
        String userAccount = userQueryRequest.getUserAccount();
        String userName = userQueryRequest.getUserName();
        String phone = userQueryRequest.getPhone();
        String userRole = userQueryRequest.getRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(uid != null, "uid", uid);
        queryWrapper.eq(StringUtils.isNotBlank(userAccount), "user_name", userAccount);
        queryWrapper.eq(ObjectUtils.isNotEmpty(phone), "phone", phone);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "role", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}
