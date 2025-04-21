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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vlib - UserController
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    IUserService iUserService;

    /**
     * 获取用户信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/UserInfo")
    @ResponseBody
    public Map<String, Object> UserInfo(@RequestBody Map<String, String> map) {
        if (null != map && map.containsKey("account")) {
            String account = map.get("account").toString();
            User user = new User();
            user.setAccount(account);
            System.out.println(account);
            List<User> userlist = iUserService.UserInfo(user);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("data", userlist);
            return dataMap;
        }
        return null;
    }

    /**
     * 插入用户信息
     * @param
     */
    @RequestMapping("/insert")
    @ResponseBody
    public boolean insert(@RequestBody Map<String, String> map) throws ParseException {

        String name=map.get("userName")==null?"":map.get("userName").toString();
        String account=map.get("account")==null?"":map.get("account").toString();
        String email=map.get("email")==null?"":map.get("email").toString();
        String password=map.get("password")==null?"":map.get("password").toString();
        String intro=map.get("intro")==null?"":map.get("intro").toString();

        String phone=map.get("phone")==null?"":map.get("phone").toString();

        int type=map.get("type")==null?0: Integer.parseInt(map.get("type"));

        long currentTimeMillis = System.currentTimeMillis();
        Timestamp create_time = new Timestamp(currentTimeMillis);

        iUserService.insertUser(name,account,email,password,intro,phone,create_time,null,type);
        return true;
    }

    /**
     * 注册：插入用户信息
     * @param
     */
    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public Map<String,Object> saveUserInfo(@RequestBody User user){

        if(null!=user){
            if(null!=user.getAccount()&&null!=user.getPassword()&&!"".equals(user.getAccount())&&!"".equals(user.getPassword())){
                String pwd= MD5Utils.encryptMD5AndSalt(user.getPassword(), MD5Utils.DEFAULT_SALT);
                user.setPassword(pwd);
                int flag = iUserService.saveUserInfo(user);
                Map<String,Object> map = new HashMap<>();
                map.put("data",flag);
                return map;
            }else {
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
    public Map<String, Object> getUserByHelperList(@RequestBody Map<String, Object> map) {

        int pageNum=map.get("pageNum").toString()==null?0:Integer.parseInt(map.get("pageNum").toString());
        int pageSize=map.get("pageSize").toString()==null?0:Integer.parseInt(map.get("pageSize").toString());
        String account=map.get("account").toString()==null?"":map.get("account").toString();
        User user=new User();
        user.setAccount(account);
        //分页查询
        PageInfo<User> userPageInfo = iUserService.getUserByHelperList(pageNum,pageSize,user);
        //得到分页中的user条目对象
        List<User> pageList = userPageInfo.getList();
        Map<String,Object> res = new HashMap<>();
        res.put("data",pageList);
        res.put("total",userPageInfo.getTotal());
        return res;
    }
}
