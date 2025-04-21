package com.fzsx.vlib.controller;


import com.alibaba.fastjson.JSONObject;
import com.fzsx.vlib.common.Constant;
import com.fzsx.vlib.common.MD5Utils;
import com.fzsx.vlib.common.TokenUtil;
import com.fzsx.vlib.entity.MenuEntity;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IMenuService;
import com.fzsx.vlib.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录后端
 * </p>
 *
 * @author
 */
@Controller
@RequestMapping("api")
public class LoginController {


     @Autowired
     IUserService userService;
     @Autowired
     IMenuService menuService;


    /**
     * 用户登录
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", user.getAccount());
        String pwd=MD5Utils.encryptMD5AndSalt(user.getPassword(), MD5Utils.DEFAULT_SALT);
        System.out.println(pwd);
        params.put("password", pwd);
        //用户账户密码验证
        User userEntity = userService.getUserByLoginInfo(params);
        JSONObject jsonObject = new JSONObject();
        if (userEntity != null) {
            //获取当前用户的权限
            List<MenuEntity> menus = menuService.listByUserId(userEntity.getUserId());

            //获取学号或者工号
            if(Constant.USER_STUDENT.equals(userEntity.getType()))
            {
                User userEntity1 = userService.getUserByAccountName(params.get("account").toString());
                if(userEntity1!=null)
                {
                    //获得用户ID
                    jsonObject.put("userId", userEntity1.getUserId());

                }
            }

            String token = TokenUtil.sign(userEntity);
            jsonObject.put("token", token);
            jsonObject.put("user", userEntity);
            jsonObject.put("menus", menus);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", 200);
            System.out.println(200);
            session.setAttribute("user",jsonObject);
        } else {
            jsonObject.put("msg", "账号或密码错误");
            jsonObject.put("code", 500);
            System.out.println(500);
        }

        return jsonObject;
    }

}
