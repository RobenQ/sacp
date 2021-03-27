package com.sacp.course.core.service;

import com.alibaba.fastjson.JSON;
import com.sacp.course.client.api.CourseApi;
import com.sacp.course.client.request.CourseClassifyRequest;
import com.sacp.course.client.request.CourseRequest;
import com.sacp.course.client.request.CourseVideoRequest;
import com.sacp.course.client.response.CourseResponse;
import com.sacp.course.client.response.CourseClassifyResponse;
import com.sacp.course.client.response.CourseVideoResponse;
import com.sacp.course.core.entity.CourseClassify;
import com.sacp.course.core.entity.CourseInfo;
import com.sacp.course.core.entity.CourseVideo;
import com.sacp.course.core.repository.CourseClassifyRepository;
import com.sacp.course.core.repository.CourseRepository;
import com.sacp.course.core.repository.CourseResourcesRepository;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.request.BlockRequest;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.MemberResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(version = "1.0")
public class CourseService implements CourseApi {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseClassifyRepository courseClassifyRepository;
    @Autowired
    private CourseResourcesRepository courseResourcesRepository;

    @DubboReference(version = "1.0")
    private ForumApi forumApi;

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @Override
    public List<CourseVideoResponse> getAllVideoByCourseId(Integer courseId) {
        List<CourseVideo> byCourseId = courseResourcesRepository.getAllVideoByCourseId(courseId);
        List<CourseVideoResponse> responses = new ArrayList<>(byCourseId.size());
        for (CourseVideo video:byCourseId) {
            CourseVideoResponse response = new CourseVideoResponse();
            BeanUtils.copyProperties(video,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public boolean deleteVideoByAuthor(Integer videoId) {
        return courseResourcesRepository.deleteVideoById(videoId);
    }

    @Override
    public long getTotalVideoByCourseId(Integer courseId) {
        return courseResourcesRepository.getVideoCountByCourseId(courseId);
    }

    @Override
    public boolean addVideo(CourseVideoRequest request) {
        CourseVideo video = new CourseVideo();
        BeanUtils.copyProperties(request,video);
        video.setUploadTime(new Date());
        return courseResourcesRepository.insertCourseVideo(video);
    }

    @Override
    public List<CourseVideoResponse> getVideoByCourseId(CourseVideoRequest request) {
        List<CourseVideo> byCourseId = courseResourcesRepository.getByCourseIdAndPage(request.getCourseId()
                ,request.getPageSize(),request.getCurrentPage());
        List<CourseVideoResponse> responses = new ArrayList<>(byCourseId.size());
        for (CourseVideo video:byCourseId) {
            CourseVideoResponse response = new CourseVideoResponse();
            BeanUtils.copyProperties(video,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public CourseResponse getCourseById(Integer id) {
        CourseResponse response = new CourseResponse();
        BeanUtils.copyProperties(courseRepository.getCourseById(id),response);
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setSacpId(response.getSacpId());
        List<MemberResponse> account = memberApi.getAccount(memberRequest);
        if (account.size()!=0)
            response.setMemberName(account.get(0).getNickName());
        return response;
    }

    @Override
    public List<CourseResponse> getCourseByClassifyIdAndPage(CourseRequest request) {
        List<CourseInfo> allCourse = courseRepository.getCourseByPage(request.getClassifyId(),request.getPageSize(),request.getCurrentPage());
        List<CourseResponse>  courseResponses = new ArrayList<>(allCourse.size());
        for (CourseInfo course:allCourse) {
            CourseResponse response = new CourseResponse();
            BeanUtils.copyProperties(course,response);
            MemberRequest memberRequest = new MemberRequest();
            memberRequest.setSacpId(response.getSacpId());
            List<MemberResponse> account = memberApi.getAccount(memberRequest);
            if (account.size()!=0)
                response.setMemberName(account.get(0).getNickName());
            courseResponses.add(response);
        }
        return courseResponses;
    }

    @Override
    public List<CourseResponse> getCourseByPage(CourseRequest request) {
        List<CourseInfo> allCourse = courseRepository.getCourseByPage(request.getSacpId(),request.getPageSize(),request.getCurrentPage());
        List<CourseResponse>  courseResponses = new ArrayList<>(allCourse.size());
        for (CourseInfo course:allCourse) {
            CourseResponse response = new CourseResponse();
            BeanUtils.copyProperties(course,response);
            courseResponses.add(response);
        }
        return courseResponses;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createCourse(CourseRequest request) {
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setBlockName(request.getCourseName());
        blockRequest.setBlockAvatar(request.getCourseAvatar());
        blockRequest.setDesc(request.getDescr());
        //先创建社区板块
        Integer blockId = forumApi.createBlock(blockRequest);
        //创建课程
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(request,courseInfo);
        courseInfo.setForumId(blockId);
        courseInfo.setCreateTime(new Date());
        courseRepository.insertCourse(courseInfo);
        return forumApi.fullBlockCourseId(courseInfo.getForumId(),courseInfo.getId());
    }

    @Override
    public List<CourseResponse> getAllCourse() {
        List<CourseInfo> allCourse = courseRepository.getAllCourse();
        List<CourseResponse>  courseResponses = new ArrayList<>(allCourse.size());
        for (CourseInfo course:allCourse) {
            CourseResponse response = new CourseResponse();
            BeanUtils.copyProperties(course,response);
            courseResponses.add(response);
        }
        return courseResponses;
    }

    @Override
    public List<CourseResponse> getCourseByClassifyId(Integer classifyId) {
        List<CourseInfo> allCourse = courseRepository.getCourseByClassifyId(classifyId);
        List<CourseResponse>  courseResponses = new ArrayList<>(allCourse.size());
        for (CourseInfo course:allCourse) {
            CourseResponse response = new CourseResponse();
            BeanUtils.copyProperties(course,response);
            courseResponses.add(response);
        }
        return courseResponses;
    }

    @Override
    public List<CourseResponse> getCourseBySacpId(String sacpId) {
        List<CourseInfo> allCourse = courseRepository.getCourseBySacpId(sacpId);
        List<CourseResponse>  courseResponses = new ArrayList<>(allCourse.size());
        for (CourseInfo course:allCourse) {
            CourseResponse response = new CourseResponse();
            BeanUtils.copyProperties(course,response);
            courseResponses.add(response);
        }
        return courseResponses;
    }

    @Override
    public List<CourseClassifyResponse> getAllClassify() {
        List<CourseClassify> allClassify = courseClassifyRepository.getAllClassify();
        List<CourseClassifyResponse> courseClassifyRespons = new ArrayList<>(allClassify.size());
        for (CourseClassify courseClassify:allClassify) {
            CourseClassifyResponse response = new CourseClassifyResponse();
            BeanUtils.copyProperties(courseClassify,response);
            courseClassifyRespons.add(response);
        }
        return courseClassifyRespons;
    }

    @Override
    public List<CourseClassifyResponse> getClassifyById(Integer classifyId) {
        List<CourseClassify> allClassify = courseClassifyRepository.getClassifyById(classifyId);
        List<CourseClassifyResponse> courseClassifyRespons = new ArrayList<>(allClassify.size());
        for (CourseClassify courseClassify:allClassify) {
            CourseClassifyResponse response = new CourseClassifyResponse();
            BeanUtils.copyProperties(courseClassify,response);
            courseClassifyRespons.add(response);
        }
        return courseClassifyRespons;
    }

    @Override
    public List<CourseClassifyResponse> getClassifyByName(String classifyName) {
        List<CourseClassify> allClassify = courseClassifyRepository.getClassifyByName(classifyName);
        List<CourseClassifyResponse> courseClassifyRespons = new ArrayList<>(allClassify.size());
        for (CourseClassify courseClassify:allClassify) {
            CourseClassifyResponse response = new CourseClassifyResponse();
            BeanUtils.copyProperties(courseClassify,response);
            courseClassifyRespons.add(response);
        }
        return courseClassifyRespons;
    }

    @Override
    public List<CourseClassifyResponse> getClassify(CourseClassifyRequest request) {
        CourseClassify classify = new CourseClassify();
        BeanUtils.copyProperties(request,classify);
        List<CourseClassify> bySeletive = courseClassifyRepository.getBySeletive(classify);
        List<CourseClassifyResponse> responses = new ArrayList<>(bySeletive.size());
        for (CourseClassify courseClassify:bySeletive) {
            CourseClassifyResponse response = new CourseClassifyResponse();
            BeanUtils.copyProperties(courseClassify,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public boolean createClassify(CourseClassifyRequest request) {
        CourseClassify courseClassify = new CourseClassify();
        BeanUtils.copyProperties(request,courseClassify);
        return courseClassifyRepository.insertCourseClassify(courseClassify);
    }

    @Override
    public boolean modifyClassify(CourseClassifyRequest request) {
        CourseClassify request2 = new CourseClassify();
        BeanUtils.copyProperties(request,request2);
        return courseClassifyRepository.updateClassify(request2);
    }

    @Override
    public long getTotalBySacpId(String sacpId) {
        return courseRepository.countBySacpId(sacpId);
    }

    @Override
    public long getTotalByClassifyId(Integer classifyId) {
        return courseRepository.countByClassifyId(classifyId);
    }
}
