package com.sacp.forum.core.entity;

public class PostWithBLOBs extends Post {
    private String reply;

    private String context;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}