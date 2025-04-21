package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.RoleEntity;
import com.fzsx.vlib.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * vlib - RoleMapper
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Component
public interface RoleMapper {
    int saveRoleInfo(RoleEntity role);

    List<RoleEntity> detail(RoleEntity role);

    List<RoleEntity> queryRoleList(RoleEntity role);

    int delete(RoleEntity role);

    int update(RoleEntity role);
}
