package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.mapper.UserMapper;
import com.fzsx.vlib.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * vlib - UserServiceImpl
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> UserInfo(User user) {
        return userMapper.UserInfo(user);
    }


    @Override
    public void insertUser(String name, String account, String email, String password, String intro, String phone, Timestamp create_time, Timestamp update_time, int type){
        userMapper.insertUser(name,account,email,password,intro,phone,create_time,update_time,type);
    }
    @Override
    public int saveUserInfo(User user){
       user.setCreate_time(new Date());
       return userMapper.saveUserInfo(user);
    }
    @Override
    public User getUserByAccountName(String accountName){return userMapper.getUserByAccountName(accountName);}
    @Override
    public User getUserByLoginInfo(Map<String,Object> map){return userMapper.getUserByLoginInfo(map);}

    /**
     * 分页查询用户信息
     * @param currentPage
     * @param pageSize
     * @param user
     * @return
     */
    public PageInfo<User> getUserByHelperList(int currentPage, int pageSize, User user){

        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<User> userList =userMapper.queryUserList(user);
        //得到分页的结果对象
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }
    /**
     * 删除用户
     * @param user
     * @return
     */
    public int delete(User user){
        return userMapper.delete(user);
    }

    /**
     * 用户明细信息
     * @param user
     * @return
     */
    public List<User> detail(User user){
        return userMapper.detail(user);
    }

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    public int update(User user) {
        user.setUpdate_time(new Date());
        return userMapper.update(user);
    }
}