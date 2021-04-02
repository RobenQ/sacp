package com.sacp.course.client.api;

import com.sacp.course.client.request.*;
import com.sacp.course.client.response.*;

import java.util.List;

public interface CourseApi {
    //course
    public List<CourseResponse> getAllCourse();

    public CourseResponse getCourseById(Integer id);

    public List<CourseResponse> getCourseByClassifyId(Integer classifyId);

    public List<CourseResponse> getCourseBySacpId(String sacpId);

    public boolean createCourse(CourseRequest request);

    public long getTotalBySacpId(String sacpId);

    public long getTotalByClassifyId(Integer classifyId);

    public List<CourseResponse> getCourseByPage(CourseRequest request);

    public List<CourseResponse> getCourseByClassifyIdAndPage(CourseRequest request);

    public boolean joinCourse(Integer courseId,String sacpId);

    public boolean isJoinCourse(Integer courseId,String sacpId);

    public List<CourseResponse> getJoinCourseByPage(CourseRequest request);

    public boolean outCourse(String sacpId,Integer courseId);

    public List<CourseResponse> getHotCourse();

    public List<CourseResponse> getNewCourse();


    //courseClassify==========================================================================
    public List<CourseClassifyResponse> getAllClassify();
    public List<CourseClassifyResponse> getClassifyById(Integer classifyId);
    public List<CourseClassifyResponse> getClassifyByName(String classifyName);
    public List<CourseClassifyResponse> getClassify(CourseClassifyRequest request);
    public boolean createClassify(CourseClassifyRequest request);
    public boolean modifyClassify(CourseClassifyRequest request);


    //courseVideo==========================================================================
    public boolean addVideo(CourseVideoRequest request);
    //分页
    public List<CourseVideoResponse> getVideoByCourseId(CourseVideoRequest request);
    //无分页
    public List<CourseVideoResponse> getAllVideoByCourseId(Integer courseId);
    public long getTotalVideoByCourseId(Integer courseId);
    public boolean deleteVideoByAuthor(Integer videoId);


    //courseResource==========================================================================
    public boolean addRes(CourseResRequest request);
    public long getTotalResByCourseId(Integer courseId);
    public List<CourseResResponse> getResByCourseId(CourseResRequest request);
    public boolean deleteResByAuthor(Integer resourceId);
    public List<CourseResResponse> getAllResByCourseId(Integer courseId);


    //discussion==========================================================================
    public boolean addDiscussion(DiscussionRequest request);
    public List<DiscussionResponse> getreplybyCourseId(Integer courseId);


    //member-Course==========================================================================
    public long getMcTotalPage(String sacpId);
}
