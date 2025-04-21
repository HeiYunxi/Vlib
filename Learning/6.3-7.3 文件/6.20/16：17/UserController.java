package com.fzsx.controller;

import com.fzsx.common.MD5Utils;
import com.fzsx.entity.User;
import com.fzsx.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/saveUserInfo")
    @ResponseBody
    public Map<String, Object> saveUserInfo(@RequestBody User user) {
        if (null != user) {
            if (null != user.getAccount() && null != user.getPassword() && !"".equals(user.getAccount()) && !"".equals(user.getPassword())) {
                String pwd = MD5Utils.encryptMD5AndSalt(user.getPassword(), MD5Utils.DEFAULT_SALT);
                user.setPassword(pwd);
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
     * 分页查询
     * @param map
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getUserByHelprList(@RequestBody Map<String, Object> map) {


        int pageNum=map.get("pageNum").toString()==null?0:Integer.parseInt(map.get("pageNum").toString());
        int pageSize=map.get("pageSize").toString()==null?0:Integer.parseInt(map.get("pageSize").toString());
        String account=map.get("account").toString()==null?"":map.get("account").toString();
        User user=new User();
        user.setAccount(account);
        //分页查询
        PageInfo<User> userPageInfo = iUserService.getUserByHelprList(pageNum,pageSize,user);
        //得到分页中的user条目对象
        List<User> pageList = userPageInfo.getList();
        Map<String,Object> res = new HashMap<>();
        res.put("data",pageList);
        res.put("total",userPageInfo.getTotal());
        return res;

    }
}
