package com.sacp.forum.client.response;

import java.io.Serializable;

public class BlockResponse implements Serializable {
    private Integer id;

    private Integer courseId;

    private Integer learnerNumber;

    private String blockName;

    private String blockAvatar;

    private String desc;

    private Integer isDelete;

    public Integer getLearnerNumber() {
        return learnerNumber;
    }

    public void setLearnerNumber(Integer learnerNumber) {
        this.learnerNumber = learnerNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockAvatar() {
        return blockAvatar;
    }

    public void setBlockAvatar(String blockAvatar) {
        this.blockAvatar = blockAvatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
