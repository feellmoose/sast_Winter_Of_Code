package com.sast.woc.service.impl;

import com.sast.woc.entity.User;
import com.sast.woc.mapper.AdminMapper;
import com.sast.woc.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    public AdminServiceImpl(AdminMapper adminMapper){
        this.adminMapper=adminMapper;
    }

    @Override
    public User findUser(String userName) {
        User user=adminMapper.selectUser(userName);
        user.setUserName(userName);
        return user;
    }

    @Override
    public void deleteUserByName(String userName) {
        adminMapper.deleteUserByName(userName);
    }

    @Override
    public boolean notExisted(String userName) {
        return adminMapper.selectUserName(userName)==null;
    }

    @Override
    public List<User> findAllUser(Integer pageNum, Integer pageSize) {
        return adminMapper.selectUserByPage(pageNum,pageSize);
    }
}
