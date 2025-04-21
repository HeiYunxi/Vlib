package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.User;
import com.github.pagehelper.PageInfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * vlib - IUserService
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */

public interface IUserService {
    List<User> UserInfo(User user);
    void insertUser(String name, String account, String email, String password, String intro, String phone, Timestamp create_time, Timestamp update_time,int type);
    public int saveUserInfo(User user);
    User getUserByAccountName(String accountName);
    User getUserByLoginInfo(Map<String,Object> map);
    PageInfo<User> getUserByHelperList(int currentPage, int pageSize, User user);
}

