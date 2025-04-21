package com.fzsx.mapper;

import com.fzsx.entity.Role;
import com.fzsx.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface RoleMapper {
    int saveRoleInfo(Role role);

    /**
     *
     * @param accountName
     * @return
     */
    Role getRoleByAccountName(String accountName);

    /**
     *
     * @param map
     * @return
     */
    Role getRoleByLoginInfo(Map<String,Object> map);

    /**
     * 分页查询
     * @param role
     * @return
     */
    List<Role> queryRoleList(Role role);

    /**
     * 删除用户
     * @param role
     * @return
     */
    int delete(Role role);

    /**
     * 角色明细信息
     * @param role
     * @return
     */
    List<Role>  detail(Role role);

    /**
     * 用户信息更新
     * @param role
     * @return
     */
     int  update(Role role);
    /**
     * 查询所有的角色
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
     * 删除指定用户的所属角色信息
     * @param userId
     * @return
     */
     int deleteUserRole(int userId);

    /**
     * 保存用户角色信息
     * @param map
     * @return
     */
    int saveUserRoleInfo(HashMap map);
    /**
     * 查询角色对应所有用户ID
     * @param roleId
     * @return
     */
    List<User>  getRoleUserList(int roleId);

    /**
     * 按角色删除角色对应用户
     * @param roleId
     * @return
     */
    int deleteRoleUser(int roleId);
}
