package com.cqupt.service.impl;


import com.cqupt.entity.UserEntity;
import com.cqupt.mapper.UserMapper;
import com.cqupt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public UserEntity getUserByAccountName(String accountName){
        return userMapper.getUserByAccountName(accountName);
    }

    @Override
    public UserEntity getUserByLoginInfo(Map<String, Object> map) {
        return userMapper.getUserByLoginInfo(map);
    }

}
