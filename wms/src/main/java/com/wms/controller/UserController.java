package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.model.common.QueryPageParam;
import com.wms.model.entity.User;
import com.wms.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wenhui
 * @since 2024-05-20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServer userServer;

    //查看
    @GetMapping("/list")
    public List<User> list() {
        return userServer.list();
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody User user) {
        return userServer.save(user);
    }

    //修改
    @PostMapping("/update")
    public boolean update(@RequestBody User user) {
        return userServer.updateById(user);
    }

    //新增或修改
    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user) {
        return userServer.saveOrUpdate(user);
    }

    //删除
    @GetMapping("/delete")
    public boolean remove(Integer uid) {
        return userServer.removeById(uid);
    }

    //查询
    @PostMapping("/select")
    public List<User> select(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getUsername, user.getUsername());
        return userServer.list(lambdaQueryWrapper);
    }

    //分页查询
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam param) {
        HashMap map = param.getData();
//        System.out.println(map);
        Page<User> page = new Page<>(param.getPageNum(), param.getPageSize());
        String username = (String) map.get("username");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getUsername, username);
        Page<User> result = userServer.page(page, lambdaQueryWrapper);
        System.out.println("total = " + result.getTotal());
        return result.getRecords();
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
