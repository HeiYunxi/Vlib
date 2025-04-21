package com.fzsx.vlib.controller;

/**
 * vlib - RoleController
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */

import com.fzsx.vlib.common.MD5Utils;
import com.fzsx.vlib.entity.RoleEntity;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IRoleService;
import com.fzsx.vlib.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vlib - RoleController
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    /**
     * 插入角色信息
     * @param
     */
    @RequestMapping("/saveRoleInfo")
    @ResponseBody
    public Map<String,Object> saveRoleInfo(@RequestBody RoleEntity role){

        if(null!=role){
            if(null!=role.getRoleName()&&null!=role.getRoleName()&&!"".equals(role.getDescription())&&!"".equals(role.getDescription())){
                int flag = iRoleService.saveRoleInfo(role);
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
    public Map<String, Object> getRoleByHelperList(@RequestBody Map<String, Object> map) {

        int pageNum=map.get("pageNum").toString()==null?0:Integer.parseInt(map.get("pageNum").toString());
        int pageSize=map.get("pageSize").toString()==null?0:Integer.parseInt(map.get("pageSize").toString());
        String roleName=map.get("roleName").toString()==null?"":map.get("roleName").toString();
        RoleEntity role=new RoleEntity();
        role.setRoleName(roleName);
        //分页查询
        PageInfo<RoleEntity> rolePageInfo = iRoleService.getRoleByHelperList(pageNum,pageSize,role);
        //得到分页中的user条目对象
        List<RoleEntity> pageList = rolePageInfo.getList();
        Map<String,Object> res = new HashMap<>();
        res.put("data",pageList);
        res.put("total",rolePageInfo.getTotal());
        return res;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody RoleEntity role){
        if(null!=role){
            if(null!=String.valueOf(role.getRoleId()) && !"".equals(String.valueOf(role.getRoleId()))){
                int flag = iRoleService.delete(role);
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
     * 查看用户信息
     * @param
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String,Object> detail(@RequestBody RoleEntity role){

        if(null!=role){
            if(null!=String.valueOf(role.getRoleName()) && !"".equals(String.valueOf(role.getRoleName()))){
                List<RoleEntity> roleList = iRoleService.detail(role);
                Map<String,Object> map = new HashMap<>();
                map.put("data",roleList);
                return map;
            }else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新：修改角色信息
     * @param
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody RoleEntity role){
        if(null!=role){
            if(null!=String.valueOf(role.getRoleId()) && !"".equals(String.valueOf(role.getRoleId()))){
                int flag = iRoleService.update(role);
                Map<String,Object> map = new HashMap<>();
                map.put("data",flag);
                return map;
            }else {
                return null;
            }
        }
        return null;
    }
}