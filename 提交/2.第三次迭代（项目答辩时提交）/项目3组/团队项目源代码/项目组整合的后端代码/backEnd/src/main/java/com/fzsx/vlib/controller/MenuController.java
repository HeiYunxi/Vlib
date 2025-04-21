package com.fzsx.vlib.controller;

import com.fzsx.vlib.entity.Menu;
import com.fzsx.vlib.service.IMenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MenuController - 菜单控制类 - 后端
 *
 * @author Heiyunxi
 * @version 0.3.0
 * 2024.6.28
 */
@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    IMenuService iMenuService;

    /**
     * 新增菜单
     *
     * @param menu 菜单实体类
     * @return Map {"data", flag-操作条目}
     * @path menu/saveMenuInfo
     */
    @RequestMapping(value = "/saveMenuInfo")
    @ResponseBody
    public Map<String, Object> saveMenuInfo(@RequestBody Menu menu) {
        if (null != menu) {
            if (null != menu.getCname() && null != menu.getPath() && !"".equals(menu.getCname()) && !"".equals(menu.getPath())) {
                if (iMenuService.checkMenuName(menu.getCname(), null)) {
                    return null;
                }
                int flag = iMenuService.saveMenuInfo(menu);
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
     * 菜单分页查询
     *
     * @param map {pageNum,pageSize,cname}
     * @return res {data,total}
     * @path menu/list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getMenuByHelprList(@RequestBody Map<String, Object> map) {
        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String cname = map.get("cname").toString() == null ? "" : map.get("cname").toString();
        Menu menu = new Menu();
        menu.setCname(cname);
        //分页查询
        PageInfo<Menu> menuPageInfo = iMenuService.getMenuByHelprList(pageNum, pageSize, menu);
        //得到分页中的user条目对象
        List<Menu> pageList = menuPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", menuPageInfo.getTotal());
        return res;

    }

    /**
     * 删除-用户对应菜单
     *
     * @param menu 菜单实体类
     * @return map {datd}
     * @path menu/delete
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Menu menu) {
        if (null != menu) {
            if (null != String.valueOf(menu.getMenuId()) && !"".equals(String.valueOf(menu.getMenuId()))) {
                //删除菜单前判断是否有角色和该菜单挂钩
                if (iMenuService.getMenuByUse(menu.getMenuId()) > 0) {
                    return null;
                }
                int flag = iMenuService.delete(menu.getMenuId());
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
     * 详情-菜单详情信息
     *
     * @param menu 菜单实体类
     * @retrun map {data}
     * @path menu/detail
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Menu menu) {
        if (null != menu) {
            if (null != String.valueOf(menu.getMenuId()) && !"".equals(String.valueOf(menu.getMenuId()))) {
                List<Menu> menuList = iMenuService.detail(menu.getMenuId());
                Map<String, Object> map = new HashMap<>();
                map.put("data", menuList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新菜单数据
     *
     * @param menu 菜单实体
     * @return map {data，操作条目数}
     * @path menu/update
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Menu menu) {
        if (null != menu) {
            if (null != String.valueOf(menu.getMenuId()) && !"".equals(String.valueOf(menu.getMenuId()))) {
                if (iMenuService.checkMenuName(menu.getCname(), menu.getMenuId())) {
                    return null;
                }
                int flag = iMenuService.update(menu);
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
     * 获取所有菜单
     *
     * @param map 菜单列表（添加子菜单）
     * @return maps {data，list}
     * @path menu/getMenuAll
     */
    @RequestMapping(value = "/getMenuAll")
    @ResponseBody
    public Map<String, Object> getMenuAll(@RequestBody Map<String, Object> map) {
        List<Menu> menuList = iMenuService.getMenuFrist();
        Map<String, Object> maps = new HashMap<>();
        List list = new ArrayList();
        list.add(getTreeOrder(menuList));
        maps.put("data", list);
        System.out.println(maps.toString());
        return maps;
    }

    /**
     * 遍历组装第一级节点
     *
     * @param menuList 菜单列表
     * @return maps {id，label}
     */
    private Map<String, Object> getTreeOrder(List<Menu> menuList) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < menuList.size(); i++) {

            Menu menu = (Menu) menuList.get(i);
            map.put("id", menu.getMenuId());
            map.put("label", menu.getCname());
            getTreeSon(menu, map);

        }
        return map;
    }

    /**
     * 生成所有的非一级节点
     *
     * @param map 菜单列表（添加子菜单）
     */
    private void getTreeSon(Menu menu, Map<String, Object> map) {
        List<Menu> sonmenuList = iMenuService.getMenuSon(menu.getMenuId());
        if (null != sonmenuList) {
            List arr = new ArrayList();

            for (int j = 0; j < sonmenuList.size(); j++) {
                Map<String, Object> map1 = new HashMap<>();
                Menu menus = (Menu) sonmenuList.get(j);
                map1.put("id", menus.getMenuId());
                map1.put("label", menus.getCname());
                arr.add(map1);
                getTreeSon(menus, map1);
            }
            map.put("children", arr);
        }
    }

    /**
     * 获取角色当前菜单权限
     *
     * @param map 角色 {roleId}
     * @return maps {data，list}
     * @path menu/getRoleMenu
     */
    @RequestMapping(value = "/getRoleMenu")
    @ResponseBody
    public Map<String, Object> getRoleMenu(@RequestBody Map<String, Object> map) {
        int roleId = map.get("roleId").toString() == null ? 0 : Integer.parseInt(map.get("roleId").toString());
        List<Menu> menuList = iMenuService.getRoleMenu(roleId);
        Map<String, Object> maps = new HashMap<>();
        List list = new ArrayList();
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            list.add(menu.getMenuId());
        }
        maps.put("data", list);
        return maps;
    }

    /**
     * 保存角色对应菜单权限
     *
     * @param map 角色实体 {roleId}
     * @return maps {data，status}
     * @path menu/saveRoleMenu
     */
    @RequestMapping(value = "/saveRoleMenu")
    @ResponseBody
    public Map<String, Object> saveRoleMenu(@RequestBody Map<String, Object> map) {
        int roleId = map.get("roleId").toString() == null ? 0 : Integer.parseInt(map.get("roleId").toString());
        List list = (List) map.get("menuList");
        Map<String, Object> maps = new HashMap<>();
        if (null != list && list.size() > 0) {
            //删除原有的角色菜单信息,新增新的角色菜单信息
            int flag = iMenuService.saveRoleMenu(roleId, list);
            maps.put("data", flag);
            return maps;
        }
        return null;
    }

}
