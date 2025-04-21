package com.fzsx.service;

import com.fzsx.entity.Role;
import com.fzsx.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public int saveUserInfo(User user);
    int delete(User user);
    User getUserByAccountName(String accountName);


    User getUserByLoginInfo(Map<String, Object> map);
    PageInfo<User> getUserByHelprList(int currentPage, int pageSize,User user);

    /**
     * 用户明细信息
     * @param user
     * @return
     */
    List<User>  detail(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int  update(User user);

}
