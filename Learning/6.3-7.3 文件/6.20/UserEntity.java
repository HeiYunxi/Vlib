package com.cqupt.entity;



import java.io.Serializable;
import java.util.Date;


public class UserEntity {
    private static final long serialVersionUID = 1L;
    // 用户id
    private int userId;
    // 姓名
    private String name;
    // 邮箱
    private String email;
    // 手机号
    private String phone;
    // 创建时间
    private Date createTime;
    // 账号名
    private String account;
    // 账号密码
    private String password;
    // 简介
    private String intro;
    // 更新时间
    private Date updateTime;
    // 用户类型
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
