package com.fzsx.controller;

import com.fzsx.common.MD5Utils;
import com.fzsx.entity.Menu;
import com.fzsx.entity.User;
import com.fzsx.service.IMenuService;
import com.fzsx.service.IUserService;
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

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    IMenuService iMenuService;

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
     * 分页查询
     *
     * @param map
     * @return
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

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Menu menu) {
        if (null != menu) {
            if (null != String.valueOf(menu.getMenuId()) && !"".equals(String.valueOf(menu.getMenuId()))) {
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
     * @param menu
     * @return
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
     * @param menuList
     * @return
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
     * @param menu
     * @param map
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
     * 查询某个角色对应选择的菜单
     * @param map
     * @return
     */
    @RequestMapping(value = "/getRoleMenu")
    @ResponseBody
    public Map<String, Object> getRoleMenu(@RequestBody Map<String, Object> map) {

        int roleId = map.get("roleId").toString() == null ? 0 : Integer.parseInt(map.get("roleId").toString());
        List<Menu> menuList = iMenuService.getRoleMenu(roleId);
        Map<String, Object> maps = new HashMap<>();
        List list = new ArrayList();
        for(int i=0;i<menuList.size();i++) {
            Menu menu=menuList.get(i);
            list.add(menu.getMenuId());
        }
        maps.put("data", list);
        return maps;
    }
    /**
     * 保存指定用户角色信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveRoleMenu")
    @ResponseBody
    public Map<String, Object> saveRoleMenu(@RequestBody Map<String, Object> map) {
        int roleId=map.get("roleId").toString()==null?0:Integer.parseInt(map.get("roleId").toString());
        List list=(List)map.get("menuList");
        Map<String, Object> maps = new HashMap<>();
        if(null!=list&&list.size()>0) {
            //删除原有的角色菜单信息
            int flag = iMenuService.deleteRoleMenu(roleId);
            //新增新的角色菜单信息
            if(flag>=0) {

                int status = iMenuService.saveRoleMenuInfo(roleId, list);
                maps.put("data", status);
            }
            return maps;
        }
        return null;

    }

}
