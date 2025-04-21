package com.example.demo.demos.web;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService iUserService;

    /**
     * 获取user用户列表数据，分页模式
     */
    @RequestMapping(value="/list")
    @ResponseBody
    public Map<String, Object> list(@RequestBody Map<String, String> map){
        String name=map.get("name")==null?"":map.get("name").toString();
        String current=map.get("current").toString();
        int pageSize=map.get("size").toString()==null?0:Integer.parseInt(map.get("size").toString());
        if(pageSize==0)return new HashMap<>();
        int pageNum=0;
        pageNum=(Integer.parseInt(current)-1)*pageSize;//计算当前开始行号
        //分页查询
        List<User> records =iUserService.getUserList(name,pageNum,pageSize);
        //查询总记录数
        Integer total=iUserService.getUserListTotal(name);
        Map<String,Object> res = new HashMap<>();
        res.put("data",records);
        res.put("total",total);
        return res;
    }
    /**
     * 获取user用户列表数据，分页插件pagehelper
     */
    @RequestMapping(value="/lists")
    @ResponseBody
    public Map<String, Object> lists(@RequestBody Map<String, String> map){
        int pageNum=map.get("pageNum").toString()==null?0:Integer.parseInt(map.get("pageNum").toString());
        int pageSize=map.get("pageSize").toString()==null?0:Integer.parseInt(map.get("pageSize").toString());

        //分页查询
        PageInfo<User> userPageInfo = iUserService.getUserByHelprList(pageNum,pageSize);
        //得到分页中的user条目对象
        List<User> pageList = userPageInfo.getList();
        Map<String,Object> res = new HashMap<>();
        res.put("data",pageList);
        res.put("total",userPageInfo.getTotal());
        return res;
    }
    /**
     * 获取user用户明细数据，按ID查询
     */
    @RequestMapping(value="/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Map<String, String> map){
        String id=map.get("id").toString();

        //查询
        List<User> users =iUserService.getUserById(id);
        Map<String,Object> res = new HashMap<>();
        res.put("data",users);
        return res;
    }
    /**
     * 保存用户信息
     * @param user
     */
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody User user) {
        iUserService.saveUser(user);
        return true;
    }
    /**
     * 修改用户信息
     * @param user
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody User user) {
        iUserService.updateUser(user);
        return true;
    }
    /**
     * 登录方法
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody User user){
        List<User> data = iUserService.getUser(user);
        if(null!=data&&data.size()>0){
            System.out.println("用户存在，登录成功");
            return true;
        }
        System.out.println("用户不存在，登录失败");
        return false;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestBody Map<String, String> map){
        String id=map.get("id").toString();
        int del= iUserService.delUser(id);
        if(del>0){
            System.out.println("用户存在，删除成功");
            return true;
        }
        System.out.println("用户不存在，删除失败");
        return false;
    }
}
