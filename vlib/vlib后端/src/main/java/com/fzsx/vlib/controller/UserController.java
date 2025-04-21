package com.fzsx.vlib.controller;

import com.fzsx.vlib.common.MD5Utils;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController - 用户控制类
 *
 * @author Heiyunxi
 * @version 0.3.0
 * 2024.6.28
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    /* 自动注解-Service层 UserService */
    @Autowired
    IUserService iUserService;

    /**
     * 用户注册（新增用户）
     *
     * @param user 用户实体类
     * @return Map (data,条数)
     * @path user/saveUserInfo
     */
    @RequestMapping(value = "/saveUserInfo")
    @ResponseBody
    public Map<String, Object> saveUserInfo(@RequestBody User user) {
        if (null != user) {
            if (null != user.getAccount() && null != user.getPassword() && !"".equals(user.getAccount()) && !"".equals(user.getPassword())) {
                //密码加密
                String pwd = MD5Utils.encryptMD5AndSalt(user.getPassword(), MD5Utils.DEFAULT_SALT);
                user.setPassword(pwd);
                //向下层传递
                int flag = iUserService.saveUserInfo(user);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 用户列表 - 分页查询
     *
     * @param map {pageNum，pageSize，account}
     * @return res {data，total}
     * @path user/list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getUserByHelprList(@RequestBody Map<String, Object> map) {
        //获取分页以及用户账号信息
        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String account = map.get("account").toString() == null ? "" : map.get("account").toString();
        //封装用户
        User user = new User();
        user.setAccount(account);
        //下层传递，分页查询
        PageInfo<User> userPageInfo = iUserService.getUserByHelprList(pageNum, pageSize, user);
        //得到分页中的user条目对象
        List<User> pageList = userPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", userPageInfo.getTotal());
        return res;
    }

    /**
     * 删除用户条目
     *
     * @param user 用户实体类
     * @return Map {data，条数}
     * @path user/delete
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody User user) {
        if (null != user) {
            if (null != String.valueOf(user.getUserId()) && !"".equals(String.valueOf(user.getUserId()))) {
                int flag = iUserService.delete(user);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 用户详情信息
     *
     * @param user 用户实体类
     * @return Map {data，用户信息列表}
     * @path user/detail
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody User user) {
        if (null != user) {
            //用户ID不为空时，向下层传达查询用户信息列表
            if (null != String.valueOf(user.getUserId()) && !"".equals(String.valueOf(user.getUserId()))) {
                List<User> userList = iUserService.detail(user);
                Map<String, Object> map = new HashMap<>();
                map.put("data", userList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新用户数据
     *
     * @param user 用户实体类
     * @return map {data，操作条目数}
     * @path user/update
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody User user) {
        if (null != user) {
            if (null != String.valueOf(user.getUserId()) && !"".equals(String.valueOf(user.getUserId()))) {
                if (user.getPassword().length() == 32) {
                    //若密码长度为32位，则是数据库中原有加密后的密码，不做处理
                } else {
                    //否则为新密码，加密后存储
                    String pwd = MD5Utils.encryptMD5AndSalt(user.getPassword(), MD5Utils.DEFAULT_SALT);
                    user.setPassword(pwd);
                }
                int flag = iUserService.update(user);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

}
