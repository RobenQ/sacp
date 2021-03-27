package com.sacp.course.client.response;

import java.io.Serializable;
import java.util.Date;

public class CourseResponse implements Serializable {
    private Integer id;

    private String sacpId;

    private String memberName;

    private String courseName;

    private String courseAvatar;

    private String descr;

    private Integer classifyId;

    private String classifyName;

    private Integer forumId;

    private long totalPage;

    private Integer pageSize;

    private Integer currentPage;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAvatar() {
        return courseAvatar;
    }

    public void setCourseAvatar(String courseAvatar) {
        this.courseAvatar = courseAvatar;
    }

    public String getSacpId() {
        return sacpId;
    }

    public void setSacpId(String sacpId) {
        this.sacpId = sacpId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getLearnerNumber() {
        return learnerNumber;
    }

    public void setLearnerNumber(Integer learnerNumber) {
        this.learnerNumber = learnerNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    private Date createTime;

    private Date modifyTime;

    private Integer learnerNumber;

    private Integer replyNumber;
}
