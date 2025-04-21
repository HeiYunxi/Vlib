package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.RoleEntity;
import com.fzsx.vlib.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * vlib - IRoleService
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface IRoleService {
    int saveRoleInfo(RoleEntity role);

    List<RoleEntity> detail(RoleEntity role);

    PageInfo<RoleEntity> getRoleByHelperList(int pageNum, int pageSize, RoleEntity role);

    int delete(RoleEntity role);

    int update(RoleEntity role);
}
