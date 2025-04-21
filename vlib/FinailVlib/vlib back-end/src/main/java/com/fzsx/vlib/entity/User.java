package com.fzsx.vlib.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * vlib - User
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class User {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private String account;
    private String email;
    private String password;
    private String intro;
    private String phone;
    private Date create_time;
    private Date update_time;
    private int type;

    public User() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", intro='" + intro + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", type=" + type +
                '}';
    }
}

