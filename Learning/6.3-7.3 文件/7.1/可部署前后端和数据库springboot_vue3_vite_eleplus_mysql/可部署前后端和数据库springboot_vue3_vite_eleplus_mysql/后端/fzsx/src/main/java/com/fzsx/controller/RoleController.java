package com.fzsx.controller;

import com.fzsx.common.MD5Utils;
import com.fzsx.entity.Role;
import com.fzsx.entity.User;
import com.fzsx.service.IRoleService;
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
@RequestMapping(value = "role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

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
     * 分页查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getRoleByHelprList(@RequestBody Map<String, Object> map) {


        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String roleName = map.get("roleName").toString() == null ? "" : map.get("roleName").toString();
        Role role = new Role();
        role.setRoleName(roleName);
        //分页查询
        PageInfo<Role> rolePageInfo = iRoleService.getRoleByHelprList(pageNum, pageSize, role);
        //得到分页中的user条目对象
        List<Role> pageList = rolePageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", rolePageInfo.getTotal());
        return res;

    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Role role) {
        if (null != role) {
            if (null != String.valueOf(role.getRoleId()) && !"".equals(String.valueOf(role.getRoleId()))) {
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

    @RequestMapping(value = "/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Role role) {
        if (null != role) {
            if (null != String.valueOf(role.getRoleId()) && !"".equals(String.valueOf(role.getRoleId()))) {
                List<Role> roleList = iRoleService.detail(role);
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
     * 更新用户数据
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Role role) {
        if (null != role) {
            if (null != String.valueOf(role.getRoleId()) && !"".equals(String.valueOf(role.getRoleId()))) {

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
     * 查询所有角色信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/roleList")
    @ResponseBody
    public Map<String, Object> roleList(@RequestBody Map<String, Object> map) {


        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        List<Role> roleLists = iRoleService.roleList(userId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;

    }

    /**
     * 查询指定用户角色信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/userRoleList")
    @ResponseBody
    public Map<String, Object> userRoleList(@RequestBody Map<String, Object> map) {


        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        List<Role> roleLists = iRoleService.userRoleList(userId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;

    }

    /**
     * 保存指定用户角色信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveUserRole")
    @ResponseBody
    public Map<String, Object> saveUserRole(@RequestBody Map<String, Object> map) {
        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        List list = (List) map.get("rolelist");
        Map<String, Object> maps = new HashMap<>();
        if (null != list && list.size() > 0) {
            //删除原有的用户角色信息
            int flag = iRoleService.deleteUserRole(userId);
            //新增新的用户角色信息
            if (flag >= 0) {
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
