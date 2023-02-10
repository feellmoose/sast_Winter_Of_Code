package com.sast.woc.service;

import com.sast.woc.entity.User;

public interface UserService {
    String addUser(User user);
    Boolean login(String userName,String password);
    User getInfo(String userName);
    boolean editInfo(String userName,User user);
    Integer getRole(String userName);
    User getAllInfo(String userName);


}
