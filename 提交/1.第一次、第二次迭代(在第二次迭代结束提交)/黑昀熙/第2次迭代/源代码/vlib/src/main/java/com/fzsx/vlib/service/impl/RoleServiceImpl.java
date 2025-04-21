package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.RoleEntity;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.mapper.RoleMapper;
import com.fzsx.vlib.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * vlib - RoleServiceImpl
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleMapper roleMapper;

    public int saveRoleInfo(RoleEntity role){
        role.setCreate_time(new Date());
        return roleMapper.saveRoleInfo(role);
    }
    public List<RoleEntity> detail(RoleEntity role){
        return roleMapper.detail(role);
    }

    @Override
    public PageInfo<RoleEntity> getRoleByHelperList(int currentPage, int pageSize, RoleEntity role) {
        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<RoleEntity> roleList =roleMapper.queryRoleList(role);
        //得到分页的结果对象
        PageInfo<RoleEntity> rolePageInfo = new PageInfo<>(roleList);
        return rolePageInfo;
    }

    @Override
    public int delete(RoleEntity role) {
        return roleMapper.delete(role);
    }

    @Override
    public int update(RoleEntity role) {
        role.setUpdate_time(new Date());
        return roleMapper.update(role);
    }
}
