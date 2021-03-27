package com.sacp.course.client.api;

import com.sacp.course.client.request.CourseClassifyRequest;
import com.sacp.course.client.request.CourseRequest;
import com.sacp.course.client.request.CourseVideoRequest;
import com.sacp.course.client.response.CourseResponse;
import com.sacp.course.client.response.CourseClassifyResponse;
import com.sacp.course.client.response.CourseVideoResponse;

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


    //courseClassify==========================================================================
    public List<CourseClassifyResponse> getAllClassify();
    public List<CourseClassifyResponse> getClassifyById(Integer classifyId);
    public List<CourseClassifyResponse> getClassifyByName(String classifyName);
    public List<CourseClassifyResponse> getClassify(CourseClassifyRequest request);
    public boolean createClassify(CourseClassifyRequest request);
    public boolean modifyClassify(CourseClassifyRequest request);


    //courseClassify==========================================================================
    public boolean addVideo(CourseVideoRequest request);
    public List<CourseVideoResponse> getVideoByCourseId(CourseVideoRequest request);
    public List<CourseVideoResponse> getAllVideoByCourseId(Integer courseId);
    public long getTotalVideoByCourseId(Integer courseId);
    public boolean deleteVideoByAuthor(Integer videoId);
}
