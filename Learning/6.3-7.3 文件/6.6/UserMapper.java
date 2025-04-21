package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    List<User>  getUser(User user);

    void saveUser(User user);
    void updateUser(User user);
    //原始分页查询
    List<User> getUserList(@Param("username")String username,@Param("current") int current, @Param("size")int size);

    //分页插件pagehelper 查询
    List<User> queryUserList();
    int getUserListTotal(@Param("username")String username);
    int delUser(String id);
}
