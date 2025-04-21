package com.fzsx.vlib.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * vlib - Apply
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */

public class Apply {

    //申请id
    private int applyId;

    //申请编号
    private String applyNum;

    //申请天数
    private int applyDay;

    //申请原因
    private String applyReason;

    //申请状态
    // 0-待提交  1-驳回  2-通过  3-待审批 4-撤回
    private int applyStatus;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //申请时间
    private Date applyDatetime;

    //审批意见
    private String reviewComments;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //审批时间
    private Date reviewDatetime;

    //用户id
    private int userId;


    private User user;

    public Apply() {
    }



    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public int getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(int applyDay) {
        this.applyDay = applyDay;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Date getReviewDatetime() {
        return reviewDatetime;
    }

    public void setReviewDatetime(Date reviewDatetime) {
        this.reviewDatetime = reviewDatetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

