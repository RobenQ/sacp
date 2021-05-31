package com.sacp.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sacp.course.client.api.CourseApi;
import com.sacp.course.client.request.CourseRequest;
import com.sacp.course.client.request.CourseResRequest;
import com.sacp.course.client.request.CourseVideoRequest;
import com.sacp.course.client.request.DiscussionRequest;
import com.sacp.course.client.response.*;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.client.response.ReplyResponse;
import com.sacp.web.annotation.CheckMemberCourse;
import com.sacp.web.response.CourseInfoResponse;
import com.sacp.web.response.UserResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @DubboReference(version = "1.0")
    private CourseApi courseApi;

    @DubboReference(version = "1.0")
    private ForumApi forumApi;

    @GetMapping("hotCourse")
    public UserResponse hotCourse(){
        List<CourseResponse> hotCourse = courseApi.getHotCourse();
        return UserResponse.buildSuccess(hotCourse);
    }

    @GetMapping("newCourse")
    public UserResponse newCourse(){
        List<CourseResponse> hotCourse = courseApi.getNewCourse();
        return UserResponse.buildSuccess(hotCourse);
    }

    @GetMapping("getAllClassify")
    public UserResponse getAllClassify(){
        List<CourseClassifyResponse> allClassify = courseApi.getAllClassify();
        return UserResponse.buildSuccess(allClassify);
    }

    @RequiresUser
    @PostMapping("createCourse")
    public UserResponse createCourse(@RequestBody CourseRequest request){
        boolean course = courseApi.createCourse(request);
        if (course)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @RequiresUser
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
    @RequiresUser
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

    @RequiresUser
    @PostMapping("addVideo")
    public UserResponse addVideo(@RequestBody CourseVideoRequest request){
        boolean b = courseApi.addVideo(request);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @RequiresUser
    @PostMapping("addRes")
    public UserResponse addRes(@RequestBody CourseResRequest request){
        boolean b = courseApi.addRes(request);
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

    @GetMapping("getTotalResPage")
    public UserResponse getTotalResPage(@RequestParam Integer courseId){
        long totalVideoByCourseId = courseApi.getTotalResByCourseId(courseId);
        return UserResponse.buildSuccess(totalVideoByCourseId);
    }

    //courseId分页查询视频
    @PostMapping("getVideoByCourseId")
    public UserResponse getVideoByCourseId(@RequestBody CourseVideoRequest request){
        List<CourseVideoResponse> courseVideoResponses = courseApi.getVideoByCourseId(request);
        return UserResponse.buildSuccess(courseVideoResponses);
    }

    //courseId分页查询视频
    @PostMapping("getResByCourseId")
    public UserResponse getResByCourseId(@RequestBody CourseResRequest request){
        List<CourseResResponse> courseVideoResponses = courseApi.getResByCourseId(request);
        return UserResponse.buildSuccess(courseVideoResponses);
    }

    @RequiresUser
    @GetMapping("deleteVideo")
    public UserResponse deleteVideo(@RequestParam Integer videoId){
        boolean b = courseApi.deleteVideoByAuthor(videoId);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @RequiresUser
    @GetMapping("deleteCourse")
    public UserResponse deleteCourse(@RequestParam Integer courseId){
        boolean b = courseApi.deleteCourseByAuthor(courseId);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @RequiresUser
    @GetMapping("deleteRes")
    public UserResponse deleteRes(@RequestParam Integer resId){
        boolean b = courseApi.deleteResByAuthor(resId);
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

    @GetMapping("getAllRes")
    public UserResponse getAllRes(@RequestParam Integer courseId){
        List<CourseResResponse> responses = courseApi.getAllResByCourseId(courseId);
        return UserResponse.buildSuccess(responses);
    }

    @RequiresUser
    @PostMapping("joinCourse")
    public UserResponse joinCourse(@RequestBody JSONObject object){
        String sacpId = object.getString("sacpId");
        Integer courseId = object.getIntValue("courseId");
        boolean joinCourse = courseApi.isJoinCourse(courseId, sacpId);
        if (joinCourse){
            return UserResponse.buildSuccess("你已加入课程，请勿重复加入！");
        }else {
            boolean b = courseApi.joinCourse(courseId, sacpId);
            if (b)
                return UserResponse.buildSuccess("加入成功！！");
            else
                return UserResponse.buildFaild();
        }
    }

    @RequiresUser
    @PostMapping("addDiscussion")
    public UserResponse addDiscussion(@RequestBody DiscussionRequest request){
        boolean joinCourse = courseApi.isJoinCourse(request.getCourseId(), request.getSacpId());
        if (!joinCourse){
            return UserResponse.buildSuccess("加入课程后才能评论！");
        }else {
            boolean b = courseApi.addDiscussion(request);
            if (b){
                return UserResponse.buildSuccess("评论成功");
            }else {
                return UserResponse.buildFaild();
            }
        }
    }

    @GetMapping("getReply")
    public UserResponse getReply(@RequestParam String courseId){
        List<DiscussionResponse> responses = courseApi.getreplybyCourseId(Integer.valueOf(courseId));
        return UserResponse.buildSuccess(responses);
    }

    @GetMapping("getJoinMb")
    public UserResponse getJoinMb(@RequestParam String sacpId){
        List<BlockResponse> responses = forumApi.getMbBySacpIdLimit(sacpId);
        for (BlockResponse block:responses) {
            if (courseApi.getCourseById(block.getCourseId()).getIsDelete()!=0)
                block = null;
        }
        return UserResponse.buildSuccess(responses);
    }

    //sacpId分页查询用户加入的课程
    @RequiresUser
    @GetMapping("getJoinTotalPage")
    public UserResponse getJoinTotalPage(@RequestParam String sacpId){
        long res = courseApi.getMcTotalPage(sacpId);
        return UserResponse.buildSuccess(res);
    }

    @RequiresUser
    @PostMapping("getJoinCourse")
    public UserResponse getJoinCourse(@RequestBody CourseRequest request){
        List<CourseResponse> courseByPage = courseApi.getJoinCourseByPage(request);
        return UserResponse.buildSuccess(courseByPage);
    }

    @RequiresUser
    @PostMapping("outCourse")
    public UserResponse outCourse(@RequestBody JSONObject request){
        String sacpId = request.getString("sacpId");
        Integer courseId = request.getIntValue("courseId");
        boolean b = courseApi.outCourse(sacpId, courseId);
        if (b)
            return UserResponse.buildSuccess("已退出课程！");
        else
            return UserResponse.buildSuccess("退出课程失败！");
    }

    @RequiresUser
    @GetMapping("getCourseReplyBySacpId")
    public UserResponse getCourseReplyBySacpId(@RequestParam String sacpId){
        List<DiscussionResponse> responses = courseApi.getAllDiscussionBySacpId(sacpId);
        return UserResponse.buildSuccess(responses);
    }



    @RequiresUser
    @PostMapping("deleteCourseReply")
    public UserResponse getCourseReplyBySacpId(@RequestBody JSONObject request){
        String sacpId = request.getString("sacpId");
        Integer replyId = request.getInteger("replyId");
        boolean b = courseApi.deleteDiscussionById(replyId);
        if (b)
            return UserResponse.buildSuccess("删除成功！");
        else
            return UserResponse.buildSuccess("删除失败！");
    }

    @CheckMemberCourse
    @PostMapping("checkUserCourse")
    public UserResponse checkUserCourse(@RequestBody JSONObject request){
        return UserResponse.buildSuccess("允许播放视频");
    }

}
