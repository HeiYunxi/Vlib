package com.fzsx.service.impl;



import com.fzsx.entity.Menu;
import com.fzsx.entity.Role;
import com.fzsx.mapper.MenuMapper;
import com.fzsx.service.IMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Menu> listByUserId(int userId) {
        return menuMapper.listByUserId(userId);
    }

    @Override
    public int save(Menu menu) {
        return menuMapper.save(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    @Override
    public int delete(int menuId) {
        return menuMapper.remove(menuId);
    }

    /**
     *
     * @param menu
     * @return
     */
    public int saveMenuInfo(Menu menu) {
        menu.setCreate_time(new Date());
        menu.setUpdate_time(new Date());
        return menuMapper.save(menu);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param menu
     * @return
     */
   public  PageInfo<Menu> getMenuByHelprList(int currentPage, int pageSize, Menu menu) {

       PageHelper.startPage(currentPage, pageSize);
       //分页查询
       List<Menu> menuList =menuMapper.queryMenuList(menu);
       //得到分页的结果对象
       PageInfo<Menu> menuPageInfo = new PageInfo<>(menuList);
       return menuPageInfo;
   }
   public List<Menu>  detail(int menuId){
       return menuMapper.detail(menuId);
   }
    /**
     * 判断菜单名称是否重复
     * @param menuName
     * @return
     */
    public boolean checkMenuName(String menuName,Integer menuId){
        Menu menu=new Menu();
        menu.setCname(menuName);
        if(null!=menuId){
            menu.setMenuId((int)menuId);
        }
        List<Menu> menus=menuMapper.queryMenuList(menu);
        if(null!=menus&&menus.size()>0){
            return true;
        }
       return false;
    }
    /**
     * 返回所有一级菜单
     * @return
     */
   public  List<Menu> getMenuFrist() {

       return menuMapper.getMenuFrist();
   }
    /**
     * 返回下级单
     * @return
     */
    public  List<Menu> getMenuSon(int menuId) {

        return menuMapper.getMenuSon(menuId);
    }
    /**
     * 查询某个角色对应选择的菜单
     * @param roleId
     * @return
     */
   public List<Menu> getRoleMenu(int roleId){
       return menuMapper.getRoleMenu(roleId);
    }
    /**
     * 删除原有的角色菜单信息
     * @param roleId
     * @return
     */
    public int deleteRoleMenu(int roleId) {
      return menuMapper.deleteRoleMenu(roleId);
    }

    /**
     * 新增新的角色菜单信息
     * @param roleId
     * @param list
     * @return
     */
    public int saveRoleMenuInfo(int roleId,List list) {
        HashMap map=new HashMap<>();
        int flag=0;
        for(int i=0;i<list.size();i++){
            map.put("roleId",roleId);
            map.put("menuId",list.get(i));
           flag=flag+ menuMapper.saveRoleMenuInfo(map);
        }
       return flag;
    }
    /**
     * 获取某个角色对应所有下级菜单信息
     * @param map
     * @return
     */
   public List<Menu>  getRoleMenuSon(Map<String,Object> map){
        return menuMapper.getRoleMenuSon(map);
    }
}
