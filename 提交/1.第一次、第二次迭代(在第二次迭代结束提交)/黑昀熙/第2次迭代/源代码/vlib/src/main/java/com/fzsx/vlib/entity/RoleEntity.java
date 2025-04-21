package com.fzsx.vlib.entity;

import java.util.Date;

/**
 * vlib - RoleEntity
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class RoleEntity {
    private static final long serialVersionUID = 1L;
    private int roleId;

    private String roleName;

    private String description;

    private Date create_time;

    private Date update_time;

    public RoleEntity() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
