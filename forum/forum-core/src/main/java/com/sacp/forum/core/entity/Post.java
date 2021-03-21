package com.sacp.forum.core.entity;

import java.util.Date;

public class Post {
    private Integer id;

    private String sacpId;

    private Integer blockId;

    private String title;

    private Integer classifyId;

    private Integer order;

    private Integer viewerNumber;

    private Integer replyNumber;

    private Integer likesNumber;

    private Date createTime;

    private Byte idDelete;

    private String context;

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
        this.sacpId = sacpId == null ? null : sacpId.trim();
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getViewerNumber() {
        return viewerNumber;
    }

    public void setViewerNumber(Integer viewerNumber) {
        this.viewerNumber = viewerNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
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

    public Byte getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(Byte idDelete) {
        this.idDelete = idDelete;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}