package com.fzsx.vlib.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzsx.vlib.common.Constant;
import com.fzsx.vlib.common.MD5Utils;

import com.fzsx.vlib.common.TokenUtil;
import com.fzsx.vlib.entity.Menu;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IMenuService;
import com.fzsx.vlib.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LoginController - 登录控制类 - 后端
 *
 * @author Heiyunxi 黑昀熙
 * @version 0.3.0
 * 2024.6.27
 */
@Controller
@RequestMapping("api")
public class LoginController {

    /* 自动注解-Service层 UserService */
    @Autowired
    IUserService userService;

    /* 自动注解-Service层 MenuService */
    @Autowired
    IMenuService menuService;

    /**
     * 用户注册（新增用户）
     *
     * @param user    用户实体类
     * @param session 会话控制 储存回话信息
     * @return Object jsonObject
     * @path api/login
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User user, HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", user.getAccount());
        String pwd = MD5Utils.encryptMD5AndSalt(user.getPassword(), MD5Utils.DEFAULT_SALT);
        System.out.println(pwd);
        params.put("password", pwd);
        //用户账户密码验证
        User User = userService.getUserByLoginInfo(params);
        JSONObject jsonObject = new JSONObject();
        if (User != null) {
            //获取当前用户的权限
            List<Menu> menus = menuService.listByUserId(User.getUserId());
            jsonObject.put("userId", User.getUserId());
            jsonObject.put("userName", User.getUserName());
            //获取学号或者工号
            if (Constant.USER_STUDENT.equals(User.getType())) {
                User User1 = userService.getUserByAccountName(params.get("account").toString());
                if (User1 != null) {
                    //获得用户ID
                    jsonObject.put("userId", User1.getUserId());
                }
            }

            String token = TokenUtil.sign(User);
            jsonObject.put("token", token);
            jsonObject.put("user", User);
            jsonObject.put("menus", menus);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", 200);
            session.setAttribute("user", jsonObject);
        } else {
            jsonObject.put("msg", "账号或密码错误");
            jsonObject.put("code", 500);
        }
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    /**
     * 根据用户获取菜单（权限）
     *
     * @param map 用户 - userId
     * @return maps 菜单列表
     * @path api/getUserMenu
     */
    @RequestMapping(value = "/getUserMenu")
    @ResponseBody
    public Map<String, Object> getUserMenu(@RequestBody Map<String, Object> map) {
        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        List<Menu> menuList = menuService.getMenuFrist();
        Map<String, Object> maps = new HashMap<>();
        List list = new ArrayList();
        list.add(getTreeOrder(menuList, userId));
        maps.put("data", list);
        System.out.println(maps.toString());
        return maps;
    }

    /**
     * 遍历组装第一级节点
     *
     * @param menuList 菜单列表
     * @param userId 用户Id
     * @return maps {id，label，elIcon}
     */
    private Map<String, Object> getTreeOrder(List<Menu> menuList, int userId) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < menuList.size(); i++) {

            Menu menu = (Menu) menuList.get(i);
            map.put("id", menu.getPath());
            map.put("label", menu.getCname());
            map.put("elIcon", menu.getIcon());
            getTreeSon(menu, map, userId);
        }
        return map;
    }

    /**
     * 生成所有的非一级节点
     *
     * @param map 菜单列表（添加子菜单）
     * @param userId 用户Id
     */
    private void getTreeSon(Menu menu, Map<String, Object> map, int userId) {
        Map<String, Object> sonmap = new HashMap<>();
        sonmap.put("menuId", menu.getMenuId());
        sonmap.put("userId", userId);
        List<Menu> sonmenuList = menuService.getRoleMenuSon(sonmap);
        if (null != sonmenuList && sonmenuList.size() > 0) {
            List arr = new ArrayList();

            for (int j = 0; j < sonmenuList.size(); j++) {
                Map<String, Object> map1 = new HashMap<>();
                Menu menus = (Menu) sonmenuList.get(j);
                map1.put("id", menus.getPath());
                map1.put("label", menus.getCname());
                map1.put("elIcon", menus.getIcon());
                arr.add(map1);
                getTreeSon(menus, map1, userId);
            }
            map.put("children", arr);
        }
    }
}
