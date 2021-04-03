package com.sacp.web.response;

import com.sacp.forum.client.response.ReplyResponse;
import com.sacp.member.client.response.MemberResponse;

import java.io.Serializable;

public class ReplyInfoResponse implements Serializable {
    private ReplyResponse reply;
    private MemberResponse member;
    private boolean isLike;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public ReplyResponse getReply() {
        return reply;
    }

    public void setReply(ReplyResponse reply) {
        this.reply = reply;
    }

    public MemberResponse getMember() {
        return member;
    }

    public void setMember(MemberResponse member) {
        this.member = member;
    }
}
