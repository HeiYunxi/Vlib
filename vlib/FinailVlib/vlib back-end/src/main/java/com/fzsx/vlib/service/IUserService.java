package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * IUserService - 用户服务 - 接口
 * 实现：userServiceImpl
 *
 * @author Heiyunxi
 * @version 0.3.0
 * 2024.6.28
 */
public interface IUserService {

    /**
     * 用户注册（新增用户）
     */
    int saveUserInfo(User user);

    /**
     * 用户列表 - 分页查询
     */
    PageInfo<User> getUserByHelprList(int currentPage, int pageSize,User user);

    /**
     * 删除用户条目
     */
    int delete(User user);

    /**
     * 用户明细信息
     */
    List<User>  detail(User user);

    /**
     * 修改用户信息
     */
    int  update(User user);

    /**
     * 根据用户账号获取用户实体
     */
    User getUserByAccountName(String accountName);

    /**
     * 根据用户登录信息获取用户实体
     */
    User getUserByLoginInfo(Map<String, Object> map);

}
