package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * User的实现类
 * */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
    @Override
    public List<User> getUserList(String name,int current,int size){
        return userMapper.getUserList(name,current,size);
    }
    public PageInfo<User> getUserByHelprList(int currentPage,int pageSize){

        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<User> userList =userMapper.queryUserList();
        //得到分页的结果对象
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }
    public int getUserListTotal(String name){
        return userMapper.getUserListTotal(name);
    }
    public int  delUser(String id){
        return userMapper.delUser(id);
    }
    public List<User> getUserById(String id){
        User user=new User();
        user.setId(Integer.parseInt(id));
        return userMapper.getUser(user);
    }
}
