package com.fzsx.service;



import com.fzsx.entity.Menu;
import com.fzsx.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface IMenuService {
	Menu get(int menuId);

	List<Menu> list(Map<String, Object> map);

	List<Menu> listByRoleId(int[] roleIds);

	List<Menu> listByUserId(int userId);

	int save(Menu menu);

	int update(Menu menu);

	int delete(int menuId);

	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	int saveMenuInfo(Menu menu);

	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param menu
	 * @return
	 */
	PageInfo<Menu> getMenuByHelprList(int currentPage, int pageSize, Menu menu);
	/**
	 * 菜单明细信息
	 * @param menuId
	 * @return
	 */
	List<Menu>  detail(int menuId);

	/**
	 * 判断菜单名称是否重复
	 * @param menuName
	 * @return
	 */
	boolean checkMenuName(String menuName,Integer menuId);

	/**
	 * 返回所有一级菜单
	 * @return
	 */
	List<Menu> getMenuFrist();

	/**
	 * 返回下级单
	 * @return
	 */
	  List<Menu> getMenuSon(int menuId);

	/**
	 * 查询某个角色对应选择的菜单
	 * @param roleId
	 * @return
	 */
	 List<Menu> getRoleMenu(int roleId);
	/**
	 * 删除原有的角色菜单信息
	 * @param roleId
	 * @return
	 */
	int deleteRoleMenu(int roleId);

	/**
	 * 新增新的角色菜单信息
	 * @param roleId
	 * @param list
	 * @return
	 */
	int saveRoleMenuInfo(int roleId,List list);

	/**
	 * 获取某个角色对应所有下级菜单信息
	 * @param map
	 * @return
	 */
	List<Menu>  getRoleMenuSon(Map<String,Object> map);
	/**
	 *获取菜单被使用情况记录数
	 * @param menuId
	 * @return
	 */
	 int getMenuByUse(int menuId);

	/**
	 * 保存指定角色下的用户列表信息,事务控制
	 * @param roleId
	 * @param list
	 * @return
	 */
	 int saveRoleMenu(int roleId,List list);
}
