package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.*;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.department.DepartmentQueryRequest;
import com.wms.pojo.dto.user.*;
import com.wms.pojo.entity.User;
import com.wms.pojo.vo.UserLoginVO;
import com.wms.service.DepartmentService;
import com.wms.service.SysPermsService;
import com.wms.service.UserService;
import com.wms.utils.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.crypto.MacSpi;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wms.service.impl.UserServiceImpl.SALT;

/**
 * @author wenhui
 * @since 2024-05-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private DepartmentService departmentService;

    @Resource
    private SysPermsService sysPermsService;

    /**
     * 用户注册功能
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('user.register')")
    public Response<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return Response.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Response userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        //使用jwt根据用户id和用户名生成token字符串
        User user = userService.getByUserAccount(userLoginRequest.getUserAccount());
        String token = JwtHelper.createToken(Long.valueOf(user.getUid()),user.getUserAccount());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        UserLoginVO userLoginVO = userService.userLogin(userAccount, userPassword, request);
        return Response.success(map);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('user.logout')")
    public Response<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = userService.userLogout(request);
        return Response.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/getLogin")
    @PreAuthorize("hasAuthority('user.getLogin')")
    public Response<UserLoginVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return Response.success(userService.getUserLoginVO(user));
    }

    /**
     * 查询用户已分配的权限
     * @param request
     * @return
     */
    @GetMapping("/info")
    public Response<List<String>> info(HttpServletRequest request){
        String token = request.getHeader("header");
        Long userId = JwtHelper.getUserId(token);
        User user = userService.getById(userId);
        List<String> permsList = sysPermsService.findUserPermsByUserId(userId);
        return Response.success(permsList);
    }

    //新增用户
    @PostMapping("/add")
    public Response<Integer> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        //默认密码12345678
        String defaultPassword = "12345678";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + defaultPassword).getBytes());
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "添加用户失败");
        }
        return Response.success(user.getUid());
    }

    //修改用户
    @PostMapping("/update")
    public Response<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getUid() == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        if (userUpdateRequest.getDepartment() != null) {
            DepartmentQueryRequest departmentQueryRequest = new DepartmentQueryRequest();
            departmentQueryRequest.setDptId(user.getDepartment());
            departmentService.count(departmentService.getQueryWrapper(departmentQueryRequest));
        }
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改用户失败");
        }
        return Response.success(true);
    }


    //删除用户
    @PostMapping("/delete")
    public Response<Boolean> removeUser(@RequestBody RemoveRequest removeRequest, HttpServletRequest request) {
        if (removeRequest == null || removeRequest.getUid() == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = userService.removeById(removeRequest.getUid());
        return Response.success(result);
    }

    //根据id获取用户（仅人力资源管理员）
    @PostMapping("/get")
    public Response<User> getUserById(Integer uid, HttpServletRequest request) {
        if (uid <= 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        User user = userService.getById(uid);
        if (user == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "添加用户失败");
        }
        return Response.success(user);
    }

    //分页查询
    @PostMapping("/listPage")
    @PreAuthorize("hasAuthority('user.listPage')")
    public Response<Page<User>> listPage(@RequestBody UserQueryRequest userQueryRequest, HttpServletRequest request) {
        int current = userQueryRequest.getCurrent();
        int size = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        return Response.success(userPage);
    }
//    @PostMapping("/listPage2")
//    public Response listPage2(@RequestBody QueryPageParam param) {
//        HashMap map = param.getData();
////        System.out.println(map);
//        Page<User> page = new Page<>(param.getPageNum(), param.getPageSize());
//        String username = (String) map.get("username");
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.like(User::getUsername, username);
//        Page<User> result = userServer.page(page, lambdaQueryWrapper);
//        System.out.println("total = " + result.getTotal());
//        return Response.success(result.getTotal(),result.getRecords());
//    }
}
