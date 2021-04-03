package com.sacp.web.response;

import com.sacp.course.client.response.CourseResponse;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.client.response.PostResponse;
import com.sacp.member.client.response.MemberResponse;

import java.io.Serializable;
import java.util.List;

public class PostInfoResponse implements Serializable {
    private PostResponse post;
    private MemberResponse memeber;
    private BlockResponse block;
    private CourseResponse course;
    private boolean isLike;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse course) {
        this.course = course;
    }

    public PostResponse getPost() {
        return post;
    }

    public void setPost(PostResponse post) {
        this.post = post;
    }

    public MemberResponse getMemeber() {
        return memeber;
    }

    public void setMemeber(MemberResponse memeber) {
        this.memeber = memeber;
    }

    public BlockResponse getBlock() {
        return block;
    }

    public void setBlock(BlockResponse block) {
        this.block = block;
    }
}
