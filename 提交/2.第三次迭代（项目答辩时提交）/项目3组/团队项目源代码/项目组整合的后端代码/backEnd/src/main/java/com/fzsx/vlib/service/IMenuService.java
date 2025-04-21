package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.Menu;
import com.fzsx.vlib.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * vlib - IMenuService - interface
 * 菜单服务层接口
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface IMenuService {
    Menu get(int menuId);

    List<Menu> list(Map<String, Object> map);

    List<Menu> listByRoleId(int[] roleIds);

    /**
     * 根据用户id获取当前用户的（菜单）权限
     * LoginController
     */
    List<Menu> listByUserId(int userId);

    /**
     * 新增保存菜单
     */
    int saveMenuInfo(Menu menu);

    /**
     * 更新菜单信息
     */
    int update(Menu menu);

    /**
     * 删除菜单信息
     */
    int delete(int menuId);

    /**
     * 判断菜单名称是否重复
     */
    boolean checkMenuName(String menuName, Integer menuId);

    /**
     * 分页查询
     */
    PageInfo<Menu> getMenuByHelprList(int currentPage, int pageSize, Menu menu);

    /**
     * 菜单明细信息
     */
    List<Menu> detail(int menuId);

    /**
     * 返回所有一级菜单
     */
    List<Menu> getMenuFrist();

    /**
     * 返回下级单
     */
    List<Menu> getMenuSon(int menuId);

    /**
     * 查询某个角色对应选择的菜单
     */
    List<Menu> getRoleMenu(int roleId);

    /**
     * 删除原有的角色菜单信息
     */
    int deleteRoleMenu(int roleId);

    /**
     * 新增新的角色菜单信息
     */
    int saveRoleMenuInfo(int roleId, List list);

    /**
     * 获取某个角色对应所有下级菜单信息
     */
    List<Menu> getRoleMenuSon(Map<String, Object> map);

    /**
     * 获取菜单被使用情况记录数
     *
     * @param menuId
     * @return
     */
    int getMenuByUse(int menuId);

    /**
     * 保存指定角色下的用户列表信息,事务控制
     *
     * @param roleId
     * @param list
     * @return
     */
    int saveRoleMenu(int roleId, List list);
}
