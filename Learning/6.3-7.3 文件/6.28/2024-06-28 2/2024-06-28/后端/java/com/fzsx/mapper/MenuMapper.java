package com.fzsx.mapper;




import com.fzsx.entity.Menu;
import com.fzsx.entity.Role;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单Mapper接口
 */
@Component
public interface MenuMapper {
    Menu get(int menuId);

    List<Menu> list(Map<String,Object> map);

    List<Menu> listByRoleId(int[] roleIds);

    List<Menu> listByUserId(int userId);

    int save(Menu menu);

    int update(Menu menu);

    int remove(int menuId);

    /**
     * 分页查询
     * @param menu
     * @return
     */
    List<Menu> queryMenuList(Menu menu);
    List<Menu> detail(int menuId);

    /**
     * 查询所有一级菜单信息
     * @return
     */
    List<Menu> getMenuFrist();
    /**
     * 查询所有下级级菜单信息，支持无限级
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
     * @param map
     * @return
     */
    int saveRoleMenuInfo(HashMap map);

    /**
     * 获取某个非一级节点下某用户对应角色对应菜单
     * @param map
     * @return
     */
    List<Menu>  getRoleMenuSon(Map<String,Object> map);
}
