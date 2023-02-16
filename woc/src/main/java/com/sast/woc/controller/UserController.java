package com.sast.woc.controller;

import com.sast.woc.ResultData.MyException;
import com.sast.woc.ResultData.ReturnCode;
import com.sast.woc.Utils.TokenUtil;
import com.sast.woc.entity.User;
import com.sast.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册功能
     * MD5加密（未使用盐值）
     * @param user 用户实体类
     * @return
     */
    @RequestMapping("/register")
    public String addUser(User user){
        return userService.addUser(user);
    }

    /**
     * 查看个人信息
     * @param token
     * @return userName+password
     */
    @GetMapping("/info")
    public Map getInfo(@RequestHeader("Token") String token){
        User user=userService.getInfo(TokenUtil.tokenVerify(token).getClaim("userName").asString());
        HashMap<String,Object> map =new HashMap();
        map.put("userName",user.getUserName());
        map.put("email",user.getEmail());
        return map;
    }

    /**
     * 修改个人信息
     * @param token
     * @param user 实体类
     * @return "success"
     */
    @RequestMapping("/edit_info")
    public String editInfo(@RequestHeader("Token") String token, User user){
        //解析token
        String userName = TokenUtil.tokenVerify(token).getClaim("userName").asString();
        if(!userName.equals(user.getUserName())){
            throw new  MyException(ReturnCode.valueOf("USERNAME_ERROR"));
        }
         if(userService.editInfo(userName,user)){
             return "success";
         }
         return "false";
    }



}