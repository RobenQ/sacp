package com.sacp.forum.core.entity;

public class BlockInfo {
    private Integer id;

    private Integer courseId;

    private String blockName;

    private String blockAvatar;

    private String desc;

    private Byte isDelete;

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
        this.blockName = blockName == null ? null : blockName.trim();
    }

    public String getBlockAvatar() {
        return blockAvatar;
    }

    public void setBlockAvatar(String blockAvatar) {
        this.blockAvatar = blockAvatar == null ? null : blockAvatar.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}