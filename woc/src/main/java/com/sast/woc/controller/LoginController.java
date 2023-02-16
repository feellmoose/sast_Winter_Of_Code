package com.sast.woc.controller;

import com.sast.woc.ResultData.MyException;
import com.sast.woc.ResultData.ReturnCode;
import com.sast.woc.Utils.TokenUtil;
import com.sast.woc.entity.User;
import com.sast.woc.service.UserService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    //全局登录
    /**
     * 完成登录功能
     * @param userName 用户名
     * @param password 密码
     * @return user
     */
    @PostMapping("/login")
    public Map login(@RequestParam(defaultValue = "") String userName, @RequestParam(defaultValue = "") String password) {
        //密码
        if(!userService.login(userName,password)) {
            throw new MyException(ReturnCode.valueOf("USERNAME_OR_PASSWORD_ERROR"));
        }
        //计算token
        //返回实例
        User user= userService.getAllInfo(userName);
        HashMap<String,Object> map=new HashMap();
        Integer role=userService.getRole(userName);
        map.put("token",TokenUtil.getToken(userName,role));
        map.put("userName",user.getUserName());
        map.put("email",user.getEmail());
        map.put("role",role);
        map.put("id",user.getId());
        return map;


    }
}

