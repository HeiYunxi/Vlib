package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.mapper.RoleMapper;
import com.fzsx.vlib.mapper.UserMapper;
import com.fzsx.vlib.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;
    @Override
    public int saveUserInfo(User user) {
        int num;
        //在数据库中查询该账号是否已存在
        String accountName = user.getAccount();
        User flag = userMapper.getUserByAccountName(accountName);
        if (flag != null) {
            return 0;
        } else {
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        num = userMapper.saveUserInfo(user);
        int userId = userMapper.getUserIdByAccountName(accountName).getUserId();

            HashMap map = new HashMap();
            map.put("userId", userId);
            map.put("roleId", 1);
            roleMapper.saveUserRoleInfo(map);

        return num;
        }
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

    /**
     * 删除用户
     * @param user
     * @return
     */
    public int delete(User user) {
        return userMapper.delete(user);
    }
    /**
     * 用户明细信息
     * @param user
     * @return
     */
   public List<User>  detail(User user) {
        return userMapper.detail(user);
    }
    /**
     * 修改用户信息
     * @param user
     * @return
     */
   public int  update(User user) {
       user.setUpdate_time(new Date());
       return userMapper.update(user);
   }

}
