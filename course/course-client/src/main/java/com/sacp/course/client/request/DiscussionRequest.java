package com.sacp.course.client.request;

import java.io.Serializable;
import java.util.Date;

public class DiscussionRequest implements Serializable {
    private Integer id;

    private Integer courseId;

    private String sacpId;

    private String memberNickname;

    private String memberAvatar;

    private Integer likesNumber;

    private Date createTime;

    private Integer isDelete;

    private String context;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSacpId() {
        return sacpId;
    }

    public void setSacpId(String sacpId) {
        this.sacpId = sacpId;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname == null ? null : memberNickname.trim();
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar == null ? null : memberAvatar.trim();
    }

    public Integer getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    @Override
    public String toString() {
        return "DiscussionRequest{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", sacpId='" + sacpId + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", memberAvatar='" + memberAvatar + '\'' +
                ", likesNumber=" + likesNumber +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                ", context='" + context + '\'' +
                '}';
    }
}