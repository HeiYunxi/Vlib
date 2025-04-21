package com.fzsx.entity;

import java.util.Date;

/**
 * 角色实体类
 */
public class Role {
    private int roleId      ;//角色ID
    private String roleName    ;//角色名称
    private String description ;//角色描述
    private Date create_time ;//角色创建时间
    private Date update_time ;//角色更新时间

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
