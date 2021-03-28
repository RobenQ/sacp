package com.sacp.web.response;

import com.sacp.course.client.response.CourseResponse;
import com.sacp.forum.client.response.BlockResponse;

import java.io.Serializable;

public class CourseInfoResponse implements Serializable {

    private CourseResponse course;
    private BlockResponse block;
    private boolean isJoin;

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse course) {
        this.course = course;
    }

    public BlockResponse getBlock() {
        return block;
    }

    public void setBlock(BlockResponse block) {
        this.block = block;
    }
}
