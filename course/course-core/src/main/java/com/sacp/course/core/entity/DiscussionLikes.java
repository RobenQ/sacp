package com.sacp.course.core.entity;

import java.util.Date;

public class DiscussionLikes {
    private Integer id;

    private Integer discussionId;

    private Integer sacpId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public Integer getSacpId() {
        return sacpId;
    }

    public void setSacpId(Integer sacpId) {
        this.sacpId = sacpId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}