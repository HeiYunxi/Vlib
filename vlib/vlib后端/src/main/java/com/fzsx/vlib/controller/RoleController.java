package com.fzsx.vlib.controller;

import com.fzsx.vlib.common.MD5Utils;
import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IRoleService;
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
 * RoleController - 菜单控制类 - 后端
 *
 * @author Heiyunxi
 * @version 0.3.0
 * 2024.6.28
 */
@Controller
@RequestMapping(value = "role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    /**
     * 保存角色信息（添加角色）
     *
     * @param role 角色实体类
     * @return Map {"data", flag-操作条目}
     * @path role/saveRoleInfo
     */
    @RequestMapping(value = "/saveRoleInfo")
    @ResponseBody
    public Map<String, Object> saveRoleInfo(@RequestBody Role role) {
        if (null != role) {
            if (null != role.getRoleName() && null != role.getDescription() && !"".equals(role.getRoleName()) && !"".equals(role.getDescription())) {

                int flag = iRoleService.saveRoleInfo(role);
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
     * 获取角色列表
     *
     * @param map {“pageNum”,“pageSize”,“roleName”}
     * @return Map {"data", “total”}
     * @path role/list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getRoleByHelprList(@RequestBody Map<String, Object> map) {
        int pageNum=map.get("pageNum").toString()==null?0:Integer.parseInt(map.get("pageNum").toString());
        int pageSize=map.get("pageSize").toString()==null?0:Integer.parseInt(map.get("pageSize").toString());
        String roleName=map.get("roleName").toString()==null?"":map.get("roleName").toString();
        Role role=new Role();
        role.setRoleName(roleName);
        //分页查询
        PageInfo<Role> rolePageInfo = iRoleService.getRoleByHelprList(pageNum,pageSize,role);
        //得到分页中的user条目对象
        List<Role> pageList = rolePageInfo.getList();
        Map<String,Object> res = new HashMap<>();
        res.put("data",pageList);
        res.put("total",rolePageInfo.getTotal());
        return res;
    }

    /**
     * 删除角色
     *
     * @param role 角色实体类
     * @return Map {"data", flag-操作条目}
     * @path role/delete
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Role role) {
        if (null != role) {
            if (null != String.valueOf(role.getRoleId()) &&!"".equals(String.valueOf(role.getRoleId()))) {
                int flag = iRoleService.delete(role);
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
     * 角色详情信息
     *
     * @param role 角色实体类
     * @return Map {"data", roleList}
     * @path role/detail
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Role role) {
        if (null != role) {
            if (null != String.valueOf(role.getRoleId()) &&!"".equals(String.valueOf(role.getRoleId()))) {
                List<Role> roleList= iRoleService.detail(role);
                Map<String, Object> map = new HashMap<>();
                map.put("data", roleList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新角色信息
     *
     * @param role 角色实体类
     * @return Map {"data", flag-操作条目}
     * @path role/update
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Role role) {
        if (null != role) {
            if (null != String.valueOf(role.getRoleId()) &&!"".equals(String.valueOf(role.getRoleId()))) {

                int flag = iRoleService.update(role);
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
     * 获取角色列表（用户分配角色左侧栏）
     *
     * @param map {userId}
     * @return maps {"data", rolelist}
     * @path role/roleList
     */
    @RequestMapping(value = "/roleList")
    @ResponseBody
    public Map<String, Object> roleList(@RequestBody Map<String, Object> map) {
        int userId=map.get("userId").toString()==null?0:Integer.parseInt(map.get("userId").toString());
        List<Role> roleLists= iRoleService.roleList(userId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;

    }

    /**
     * 根据用户ID获取角色列表
     *
     * @param map {userId}
     * @return maps {"data", rolelist}
     * @path role/userRoleList
     */
    @RequestMapping(value = "/userRoleList")
    @ResponseBody
    public Map<String, Object> userRoleList(@RequestBody Map<String, Object> map) {
        int userId=map.get("userId").toString()==null?0:Integer.parseInt(map.get("userId").toString());
        List<Role> roleLists= iRoleService.userRoleList(userId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;
    }

    /**
     * 保存用户角色信息
     *
     * @param map
     * @path role/saveUserRole
     */
    @RequestMapping(value = "/saveUserRole")
    @ResponseBody
    public Map<String, Object> saveUserRole(@RequestBody Map<String, Object> map) {
        int userId=map.get("userId").toString()==null?0:Integer.parseInt(map.get("userId").toString());
        List list=(List)map.get("rolelist");
        Map<String, Object> maps = new HashMap<>();
        if(null!=list&&list.size()>0) {
            //删除原有的用户角色信息
            int flag = iRoleService.deleteUserRole(userId);
            //新增新的用户角色信息
            if(flag>=0) {
                int status = iRoleService.saveUserRoleInfo(userId, list);
                maps.put("data", status);
            }
            return maps;
        }
        return null;
    }

    /**
     * 查询角色对应所有用户ID
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getRoleUserList")
    @ResponseBody
    public Map<String, Object> getRoleUserList(@RequestBody Map<String, Object> map) {


        int roleId = map.get("roleId").toString() == null ? 0 : Integer.parseInt(map.get("roleId").toString());
        List<User> roleLists = iRoleService.getRoleUserList(roleId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;

    }

    /**
     * 保存指定角色下的用户列表信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveRoleUserList")
    @ResponseBody
    public Map<String, Object> saveRoleUserList(@RequestBody Map<String, Object> map) {
        int roleId = map.get("roleId").toString() == null ? 0 : Integer.parseInt(map.get("roleId").toString());
        List list = (List) map.get("userList");
        Map<String, Object> maps = new HashMap<>();

        //删除原有的角色菜单信息
        int flag = iRoleService.saveRoleUserList(roleId, list);
        maps.put("data", flag);
        return maps;

    }
}
