package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.mapper.RoleMapper;
import com.fzsx.vlib.mapper.UserMapper;
import com.fzsx.vlib.service.IRoleService;
import com.fzsx.vlib.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleMapper roleMapper;

    public int saveRoleInfo(Role role) {
        role.setCreate_time(new Date());
        role.setUpdate_time(new Date());
        return roleMapper.saveRoleInfo(role);
    }

    @Override
    public Role getUserByAccountName(String accountName) {
        return roleMapper.getRoleByAccountName(accountName);
    }

    @Override
    public Role getUserByLoginInfo(Map<String, Object> map) {
        return roleMapper.getRoleByLoginInfo(map);
    }

    /**
     * 分页查询用户信息
     *
     * @param currentPage
     * @param pageSize
     * @param role
     * @return
     */
    public PageInfo<Role> getRoleByHelprList(int currentPage, int pageSize, Role role) {

        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<Role> roleList = roleMapper.queryRoleList(role);
        //得到分页的结果对象
        PageInfo<Role> rolePageInfo = new PageInfo<>(roleList);
        return rolePageInfo;
    }

    /**
     * 删除用户
     *
     * @param role
     * @return
     */
    public int delete(Role role) {
        return roleMapper.delete(role);
    }

    /**
     * 用户明细信息
     *
     * @param role
     * @return
     */
    public List<Role> detail(Role role) {
        return roleMapper.detail(role);
    }

    /**
     * 修改用户信息
     *
     * @param role
     * @return
     */
    public int update(Role role) {
        role.setUpdate_time(new Date());
        return roleMapper.update(role);
    }

    /**
     * 查询用户的角色
     *
     * @param userId
     * @return
     */
    public List<Role> roleList(int userId) {
        return roleMapper.roleList(userId);
    }

    /**
     * 查询指定用户角色信息
     *
     * @param userId
     * @return
     */
    public List<Role> userRoleList(int userId) {
        return roleMapper.userRoleList(userId);
    }

    /**
     * 删除指定用户的所属角色信息
     *
     * @param userId
     * @return
     */
    public int deleteUserRole(int userId) {

        return roleMapper.deleteUserRole(userId);
    }

    /**
     * 插入用户角色信息
     *
     * @param userId
     * @param list
     * @return
     */
    public int saveUserRoleInfo(int userId, List list) {
        if (list.size() > 0) {
            int flag = 0;
            for (int i = 0; i < list.size(); i++) {
                String roleid = list.get(i).toString();
                HashMap map = new HashMap();
                map.put("userId", userId);
                map.put("roleId", roleid);
                flag = flag + roleMapper.saveUserRoleInfo(map);
            }
            return flag;
        } else {
            return 0;
        }
    }

    /**
     * 查询角色对应所有用户ID
     *
     * @param roleId
     * @return
     */
    public List<User> getRoleUserList(int roleId) {
        return roleMapper.getRoleUserList(roleId);
    }

    /**
     * 保存指定角色下的用户列表信息
     *
     * @param roleId
     * @param list
     * @return
     */
    public int saveRoleUserList(int roleId, List list) {
        //删除原来角色对应用户信息
        int flag = roleMapper.deleteRoleUser(roleId);
        //新增新的角色菜单信息
        if (flag >= 0) {
            HashMap map = new HashMap<>();
            flag = 0;
            if (list.size() == 0) {
                //清除所有用户
                flag = 0;
            }
            //插入新的角色对应用户信息
            for (int i = 0; i < list.size(); i++) {
                LinkedHashMap usermap = (LinkedHashMap) list.get(i);
                map.put("roleId", roleId);
                map.put("userId", usermap.get("userId"));
                flag = flag + roleMapper.saveUserRoleInfo(map);
            }
            return flag;
        }
        return 0;
    }

    /**
     * 获取教师列表
     *
     * @return teacherList
     */
    public List<User> getTeacherList(){
        return roleMapper.getTeacherList();
    }
}
