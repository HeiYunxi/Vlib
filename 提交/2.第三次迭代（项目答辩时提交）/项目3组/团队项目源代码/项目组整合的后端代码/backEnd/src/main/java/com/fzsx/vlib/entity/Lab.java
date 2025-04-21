package com.fzsx.vlib.entity;

import java.util.Date;

/**
 * vlib - Lab
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class Lab {


    private static final long serialVersionUID = 1L;

    private int labId;
    //编号
    private String labNumber;
    //名称
    private String labName;
    //位置
    private String position;
    //容量
    private int capacity;

    private int managerId;
    private String userName;
    //面积
    private double area;
    //创建时间
    private Date create_time;

    //修改时间
    private Date update_time;

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(String labNumber) {
        this.labNumber = labNumber;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    public String getManagerName() {
        return userName;
    }

    public void setManagerName(String managerName) {
        this.userName = managerName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
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
