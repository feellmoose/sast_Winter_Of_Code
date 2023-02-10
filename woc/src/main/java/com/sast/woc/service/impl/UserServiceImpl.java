package com.sast.woc.service.impl;

import com.sast.woc.ResultData.MyException;
import com.sast.woc.ResultData.ReturnCode;
import com.sast.woc.Utils.TokenUtil;
import com.sast.woc.entity.User;
import com.sast.woc.mapper.UserMapper;
import com.sast.woc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;


@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //实现注册功能
    @Override
    public String addUser(User user) {
        //检查是否已经存在该童鞋注册的账号，若有则“此人已经注册“
        if (userMapper.selectIdByName(user.getUserName()) != null) {
            throw new MyException(ReturnCode.valueOf("USERNAME_ALREADY_EXISTS"));
        }
        //加密(未加盐)
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        //添加用户到数据库
        userMapper.addUser(user);
        //返回成功
        return "success";
    }

    //登录
    @Override
    public Boolean login(String userName, String pwd) {
        //检查密码并返回结果
        return DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8)).equals(userMapper.selectPwd(userName));
    }

    //获取信息
    @Override
    public User getInfo(String userName) {
        User user = userMapper.selectByName(userName);
        user.setUserName(userName);
        return user;
    }

    //编辑信息
    @Override
    public boolean editInfo(String userName, User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        userMapper.editUserByName(user);
        return true;
    }

    @Override
    public Integer getRole(String userName) {
        return userMapper.selectRoleByName(userName);
    }

    @Override
    public User getAllInfo(String userName) {
        User user = userMapper.selectAllByName(userName);
        user.setUserName(userName);
        return user;
    }
}
