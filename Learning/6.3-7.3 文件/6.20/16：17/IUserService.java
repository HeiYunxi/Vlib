package com.fzsx.service;

import com.fzsx.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface IUserService {
    public int saveUserInfo(User user);
    User getUserByAccountName(String accountName);


    User getUserByLoginInfo(Map<String, Object> map);
    PageInfo<User> getUserByHelprList(int currentPage, int pageSize,User user);
}
