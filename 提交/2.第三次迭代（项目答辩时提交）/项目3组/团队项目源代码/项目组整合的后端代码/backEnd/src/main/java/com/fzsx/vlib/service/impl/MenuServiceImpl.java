package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Menu;
import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.mapper.MenuMapper;
import com.fzsx.vlib.service.IMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vlib - MenuServiceImpl
 * 菜单服务层 实体类 实现IMenuService接口
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Menu get(int menuId) {
        return menuMapper.get(menuId);
    }

    @Override
    public List<Menu> list(Map<String, Object> map) {
        return menuMapper.list(map);
    }
    @Override
    public List<Menu> listByRoleId(int[] roleIds) {
        return menuMapper.listByRoleId(roleIds);
    }
    /**
     * listByUserId 根据用户id获取当前用户的（菜单）权限
     *
     * @param userId 用户ID
     * @return List<Menu> 菜单列表
     */
    @Override
    public List<Menu> listByUserId(int userId) {
        return menuMapper.listByUserId(userId);
    }

    /**
     * update 更新菜单信息
     *
     * @param menu 菜单实体
     * @return int 更细条目数
     */
    @Override
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    /**
     * delete 删除菜单信息
     *
     * @param menuId 菜单Id
     * @return int 删除条目数
     */
    @Override
    public int delete(int menuId) {
        return menuMapper.remove(menuId);
    }

    /**
     * 菜单详情
     *
     * @param menuId 菜单Id
     * @return List<Menu>
     */
    public List<Menu> detail(int menuId) {
        return menuMapper.detail(menuId);
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单实体类
     * @return int 新增条目数
     */
    public int saveMenuInfo(Menu menu) {
        menu.setCreate_time(new Date());
        menu.setUpdate_time(new Date());
        return menuMapper.save(menu);
    }

    /**
     * 菜单列表分页
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param menu        菜单实体
     * @return PageInfo<Menu>
     */
    public PageInfo<Menu> getMenuByHelprList(int currentPage, int pageSize, Menu menu) {

        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<Menu> menuList = menuMapper.queryMenuList(menu);
        //得到分页的结果对象
        PageInfo<Menu> menuPageInfo = new PageInfo<>(menuList);
        return menuPageInfo;
    }


    /**
     * 判断菜单名称是否重复
     *
     * @param menuName
     * @return
     */
    public boolean checkMenuName(String menuName, Integer menuId) {
        Menu menu = new Menu();
        menu.setCname(menuName);
        if (null != menuId) {
            menu.setMenuId((int) menuId);
        }
        List<Menu> menus = menuMapper.queryMenuList(menu);
        if (null != menus && menus.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回所有一级菜单
     *
     * @return
     */
    public List<Menu> getMenuFrist() {

        return menuMapper.getMenuFrist();
    }

    /**
     * 返回下级单
     *
     * @return
     */
    public List<Menu> getMenuSon(int menuId) {

        return menuMapper.getMenuSon(menuId);
    }

    /**
     * 查询某个角色对应选择的菜单
     *
     * @param roleId
     * @return
     */
    public List<Menu> getRoleMenu(int roleId) {
        return menuMapper.getRoleMenu(roleId);
    }

    /**
     * 删除原有的角色菜单信息
     *
     * @param roleId
     * @return
     */
    public int deleteRoleMenu(int roleId) {
        return menuMapper.deleteRoleMenu(roleId);
    }

    /**
     * 新增新的角色菜单信息
     *
     * @param roleId
     * @param list
     * @return
     */
    public int saveRoleMenuInfo(int roleId, List list) {
        HashMap map = new HashMap<>();
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            map.put("roleId", roleId);
            map.put("menuId", list.get(i));
            flag = flag + menuMapper.saveRoleMenuInfo(map);
        }
        return flag;
    }

    /**
     * 获取某个角色对应所有下级菜单信息
     *
     * @param map
     * @return
     */
    public List<Menu> getRoleMenuSon(Map<String, Object> map) {
        return menuMapper.getRoleMenuSon(map);
    }

    /**
     *获取菜单被使用情况记录数
     * @param menuId
     * @return
     */
    public int getMenuByUse(int menuId) {
        return menuMapper.getMenuByUse(menuId);

    }
    /**
     * 保存指定角色下的用户列表信息,事务控制
     * @param roleId
     * @param list
     * @return
     */
    public int saveRoleMenu(int roleId,List list){
//删除原有的角色菜单信息
        int flag = deleteRoleMenu(roleId);
        //新增新的角色菜单信息
        if(flag>=0) {
            int status = saveRoleMenuInfo(roleId, list);
            return  status;
        }
        return 0;
    }
}
