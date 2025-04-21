package com.example.demo.service;

import com.example.demo.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IUserService {
    List<User> getUser(User user);

    void saveUser(User user);
    void updateUser(User user);
    List<User> getUserList(String name,int current,int size);
    PageInfo<User> getUserByHelprList(int currentPage,int pageSize);
    List<User> getUserById(String id);
    int getUserListTotal(String name);
    int delUser(String id);
}
