package com.fzsx.vlib.entity;


import java.io.Serializable;
import java.util.Date;


public class MenuEntity{
    private static final long serialVersionUID = 1L;
    // 菜单主键id
    private int menuId;
    // 中文名称
    private String cname;
    // 英文名称
    private String ename;
    // 接口路径
    private String path;
    // 页面路径
    private String component;
    // 菜单图标
    private String icon;
    //父菜单id
    private int pid;
    //排序
    private int sort;
    //创建时间
    private Date createDate;
    //修改时间
    private Date updateDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
