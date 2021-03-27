package com.sacp.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sacp.course.client.api.CourseApi;
import com.sacp.course.client.request.CourseRequest;
import com.sacp.course.client.request.CourseVideoRequest;
import com.sacp.course.client.response.CourseClassifyResponse;
import com.sacp.course.client.response.CourseResponse;
import com.sacp.course.client.response.CourseVideoResponse;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.web.response.CourseInfoResponse;
import com.sacp.web.response.UserResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @DubboReference(version = "1.0")
    private CourseApi courseApi;

    @DubboReference(version = "1.0")
    private ForumApi forumApi;

    @GetMapping("getAllClassify")
    public UserResponse getAllClassify(){
        List<CourseClassifyResponse> allClassify = courseApi.getAllClassify();
        return UserResponse.buildSuccess(allClassify);
    }

    @PostMapping("createCourse")
    public UserResponse createCourse(@RequestBody CourseRequest request){
        boolean course = courseApi.createCourse(request);
        if (course)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @GetMapping("getTotalPage")
    public UserResponse getTotalPage(@RequestParam String sacpId){
        long totalBySacpId = courseApi.getTotalBySacpId(sacpId);
        return UserResponse.buildSuccess(totalBySacpId);
    }

    @GetMapping("getTotalPage2")
    public UserResponse getTotalPage2(@RequestParam Integer classifyId){
        long totalBySacpId = courseApi.getTotalByClassifyId(classifyId);
        return UserResponse.buildSuccess(totalBySacpId);
    }

    //sacpId查询分页
    @PostMapping("getUserCourse")
    public UserResponse getUserCourse(@RequestBody CourseRequest request){
        List<CourseResponse> courseByPage = courseApi.getCourseByPage(request);
        return UserResponse.buildSuccess(courseByPage);
    }

    //
    @PostMapping("getUserCourse2")
    public UserResponse getUserCourse2(@RequestBody CourseRequest request){
        List<CourseResponse> courseByPage = courseApi.getCourseByClassifyIdAndPage(request);
        return UserResponse.buildSuccess(courseByPage);
    }

    @PostMapping("getCourseById")
    public UserResponse getCourseById(@RequestBody JSONObject courseId){
        CourseInfoResponse courseInfoResponse = new CourseInfoResponse();

        CourseResponse courseById = courseApi.getCourseById(courseId.getInteger("courseId"));
        courseInfoResponse.setCourse(courseById);

//        BlockResponse blockById = forumApi.getById(courseById.getForumId());
//        courseInfoResponse.setBlock(blockById);

        return UserResponse.buildSuccess(courseInfoResponse);
    }

    @PostMapping("addVideo")
    public UserResponse addVideo(@RequestBody CourseVideoRequest request){
        boolean b = courseApi.addVideo(request);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @GetMapping("getTotalVideoPage")
    public UserResponse getTotalVideoPage(@RequestParam Integer courseId){
        long totalVideoByCourseId = courseApi.getTotalVideoByCourseId(courseId);
        return UserResponse.buildSuccess(totalVideoByCourseId);
    }

    //courseId分页查询视频
    @PostMapping("getVideoByCourseId")
    public UserResponse getVideoByCourseId(@RequestBody CourseVideoRequest request){
        List<CourseVideoResponse> courseVideoResponses = courseApi.getVideoByCourseId(request);
        return UserResponse.buildSuccess(courseVideoResponses);
    }

    @GetMapping("deleteVideo")
    public UserResponse deleteVideo(@RequestParam Integer videoId){
        boolean b = courseApi.deleteVideoByAuthor(videoId);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @GetMapping("getAllVideo")
    public UserResponse getAllVideo(@RequestParam Integer courseId){
        List<CourseVideoResponse> responses = courseApi.getAllVideoByCourseId(courseId);
        return UserResponse.buildSuccess(responses);
    }
}
