package com.fzsx.service.impl;

import com.fzsx.entity.User;
import com.fzsx.mapper.UserMapper;
import com.fzsx.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int saveUserInfo(User user) {
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        return userMapper.saveUserInfo(user);
    }
    @Override
    public User getUserByAccountName(String accountName){
        return userMapper.getUserByAccountName(accountName);
    }

    @Override
    public User getUserByLoginInfo(Map<String, Object> map) {
        return userMapper.getUserByLoginInfo(map);
    }

    /**
     * 分页查询用户信息
     * @param currentPage
     * @param pageSize
     * @param user
     * @return
     */
    public PageInfo<User> getUserByHelprList(int currentPage, int pageSize,User user){

        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<User> userList =userMapper.queryUserList(user);
        //得到分页的结果对象
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }
}
