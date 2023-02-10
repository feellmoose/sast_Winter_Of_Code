package com.sast.woc.controller;

import com.sast.woc.entity.User;
import com.sast.woc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.List;

/**
 * @Author xun
 * @create 2023/1/3 17:13
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public void setAdminController(AdminService adminService){
        this.adminService=adminService;
    }
    /**
     * 根据用户名删除用户
     * @param userName 用户名
     * @return
     */
    @RequestMapping("/del_user")
    public String delUser(String userName) {
        if(adminService.notExisted(userName)){return "数据不存在";}
        adminService.deleteUserByName(userName);
        if(!adminService.notExisted(userName)){return "删除失败";}
        return "success";
    }


    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return
     */
    @GetMapping("/find_user_info")
    public User findUser(String userName) {
        User user= adminService.findUser(userName);
        user.setPassword(null);
        //只返回部分信息
        return user;
    }


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/show_all")
    public List<User> showAllUser(Integer pageNum, Integer pageSize){
        return adminService.findAllUser(pageNum,pageSize);

    }



}
