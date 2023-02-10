package com.sast.woc.service;


import com.sast.woc.entity.User;

import javax.naming.Name;
import java.util.List;

public interface AdminService {

    User findUser(String userName);

    void deleteUserByName(String userName);

    boolean notExisted(String userName);
    List<User> findAllUser(Integer pageNum,Integer pageSize);

}
