package com.sast.woc.mapper;

import com.sast.woc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.RequestingUserName;

@Mapper
@Repository
public interface UserMapper {
    // 示例，可以去resources->mapper中查看UserMapper.xml文件的内容。
    void addUser(@Param("user")User user);
    Integer selectIdByName(@Param("userName") String userName);
    String selectPwd(@Param("userName") String userName);
    User selectByName(@Param("userName") String userName);
    void editUserByName(@Param("user") User user);
    Integer selectRoleByName(@Param("userName") String userName);
    User selectAllByName(@Param("userName") String userName);

}
