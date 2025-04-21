package com.cqupt.mapper;



import com.cqupt.entity.MenuEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单Mapper接口
 */
@Component
public interface MenuMapper  {
    MenuEntity get(int menuId);

    List<MenuEntity> list(Map<String,Object> map);

    List<MenuEntity> listByRoleId(int[] roleIds);

    List<MenuEntity> listByUserId(int userId);

    int save(MenuEntity menu);

    int update(MenuEntity menu);

    int remove(int menuId);


}
