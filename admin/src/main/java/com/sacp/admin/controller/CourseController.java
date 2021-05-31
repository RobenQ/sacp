package com.sacp.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.course.client.api.CourseApi;
import com.sacp.course.client.request.CourseClassifyRequest;
import com.sacp.course.client.request.CourseRequest;
import com.sacp.course.client.request.DiscussionRequest;
import com.sacp.course.client.response.CourseClassifyResponse;
import com.sacp.course.client.response.CourseResponse;
import com.sacp.course.client.response.DiscussionResponse;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class CourseController {

    @DubboReference(version = "1.0")
    private CourseApi courseApi;
    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @GetMapping("getallclassify")
    public AdminResponse getAllClassify() {
        List<CourseClassifyResponse> allClassify = courseApi.getAllClassify();
        return AdminResponse.buildSuccess("查询全部课程成功",allClassify);
    }

    @PostMapping("addclassify")
    public AdminResponse addClassify(@RequestBody JSONObject courseClassify){
        List<CourseClassifyResponse> classifyName = courseApi.getClassifyByName(courseClassify.getString("classifyName"));
        if (classifyName.size()!=0)
            return AdminResponse.buildFaild("课程类别名称重复!");
        CourseClassifyRequest courseClassify2 = new CourseClassifyRequest();
        courseClassify2.setClassifyName(courseClassify.getString("classifyName"));
        courseClassify2.setDescr(courseClassify.getString("descr"));
        boolean classify = courseApi.createClassify(courseClassify2);
        if (classify)
            return AdminResponse.buildSuccess("添加成功！"
                    , courseApi.getClassifyByName(courseClassify.getString("classifyName")).get(0));
        else
            return AdminResponse.buildFaild("添加失败!");
    }

    @PostMapping("updateclassify")
    public AdminResponse updateClassify(@RequestBody CourseClassifyRequest request){
        boolean res = courseApi.modifyClassify(request);
        if (courseApi.getClassifyByName(request.getClassifyName())!=null)
            return AdminResponse.buildFaild("修改失败!");
        if (res)
            return AdminResponse.buildSuccess("修改成功！");
        else
            return AdminResponse.buildFaild("修改失败!");
    }

    @PostMapping("getclassify")
    public AdminResponse getClassify(@RequestBody CourseClassifyRequest request){
        List<CourseClassifyResponse> classifys = courseApi.getClassify(request);
        return AdminResponse.buildSuccess(classifys);
    }

    @PostMapping("searchCourse")
    public AdminResponse searchCourse(@RequestBody JSONObject request){
        String nickName = request.getString("author");
        String courseName = request.getString("courseName");
        int classifyId = request.getIntValue("classifyId");
        String sacpId = null;
        if (nickName!=null && !("".equals(nickName))){
            LoginResponse authInfo = memberApi.getAuthInfo(nickName);
            if (authInfo!=null)
                sacpId = authInfo.getSacpId();
        }
        CourseRequest courseRequest = new CourseRequest();
        if (sacpId!=null)
            courseRequest.setSacpId(sacpId);
        if (courseName!=null)
            courseRequest.setCourseName(courseName);
        if (classifyId!=0)
            courseRequest.setClassifyId(classifyId);
        List<CourseResponse> courseResponses = courseApi.getCourse(courseRequest);

        return AdminResponse.buildSuccess(courseResponses);
    }

    @PostMapping("searchDiscussion")
    public AdminResponse searchDiscussion(@RequestBody JSONObject request){
        String nickName = request.getString("author");
        JSONArray createTime1 = request.getJSONArray("createTime");
        List<Date> createTime = null;
        if (createTime1!=null){
            createTime = createTime1.toJavaList(Date.class);
        }
        int courseId = request.getIntValue("courseId");
        //log.info("createTime={}-{}",
        //        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime.get(0)),
        //        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime.get(1)));

        DiscussionRequest discussionRequest = new DiscussionRequest();
        if (nickName!=null && !nickName.isEmpty())
            discussionRequest.setMemberNickname(nickName);
        if (courseId!=0)
            discussionRequest.setCourseId(courseId);
        List<DiscussionResponse> discussionResponses = courseApi.getDiscussion(discussionRequest,createTime);
        return AdminResponse.buildSuccess(discussionResponses);
    }

    @PostMapping("offLineCourse")
    public AdminResponse offLineCourse(@RequestBody JSONObject request){
        Integer courseId = request.getInteger("courseId");
        boolean b = courseApi.deleteCourseByAuthor(courseId);
        if (b)
            return AdminResponse.buildSuccess();
        else
            return AdminResponse.buildFaild();
    }

    @PostMapping("offLineDiscussion")
    public AdminResponse offLineDiscussion(@RequestBody JSONObject request){
        Integer discussionId = request.getInteger("discussionId");
        boolean b = courseApi.deleteDiscussionById(discussionId);
        if (b)
            return AdminResponse.buildSuccess();
        else
            return AdminResponse.buildFaild();
    }

    @PostMapping("onLineCourse")
    public AdminResponse onLineCourse(@RequestBody JSONObject request){
        Integer courseId = request.getInteger("courseId");
        boolean b = courseApi.recoveryCourseById(courseId);
        if (b)
            return AdminResponse.buildSuccess();
        else
            return AdminResponse.buildFaild();
    }

    @PostMapping("onLineDiscussion")
    public AdminResponse onLineDiscussion(@RequestBody JSONObject request){
        Integer discussionId = request.getInteger("discussionId");
        boolean b = courseApi.recoveryDiscussionById(discussionId);
        if (b)
            return AdminResponse.buildSuccess();
        else
            return AdminResponse.buildFaild();
    }

}
