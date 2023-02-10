package com.sast.woc.mapper;

import com.sast.woc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    User selectUser(@Param("userName") String userName);
    void deleteUserByName(@Param("userName")String userName);
    Integer selectUserName(@Param("userName") String userName);
    List<User> selectUserByPage(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pagSize);


}
