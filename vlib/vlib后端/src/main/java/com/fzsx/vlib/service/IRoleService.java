package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    int saveRoleInfo(Role role);

    /**
     * 删除用户
     * @param role
     * @return
     */
    int delete(Role role);

    Role getUserByAccountName(String accountName);


    Role getUserByLoginInfo(Map<String, Object> map);
    PageInfo<Role> getRoleByHelprList(int currentPage, int pageSize,Role role);

    /**
     * 用户明细信息
     * @param role
     * @return
     */
    List<Role>  detail(Role role);

    /**
     * 修改用户信息
     * @param role
     * @return
     */
    int  update(Role role);

    /**
     * 查询所有角色
     * @param userId
     * @return
     */
    List<Role> roleList(int userId);

    /**
     * 查询指定用户角色信息
     * @param userId
     * @return
     */
    List<Role>  userRoleList(int userId);

    /**
     * 指定用户的所属角色信息
     * @param userId
     * @return
     */
    int deleteUserRole(int userId);

    /**
     * 插入用户角色信息
     * @param userId
     * @param list
     * @return
     */
    int saveUserRoleInfo(int userId,List list);

    /**
     * 查询角色对应所有用户ID
     * @param roleId
     * @return
     */
    List<User>  getRoleUserList(int roleId);

    /**
     * 保存指定角色下的用户列表信息
     * @param roleId
     * @param list
     * @return
     */
    int saveRoleUserList(int roleId,List list);

    /**
     * 获取教师列表
     *
     * @return maps {"data", teacherList}
     */
    List<User> getTeacherList();
}
