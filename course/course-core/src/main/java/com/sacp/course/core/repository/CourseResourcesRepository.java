package com.sacp.course.core.repository;

import com.sacp.course.core.entity.CourseVideo;
import com.sacp.course.core.entity.CourseVideoExample;
import com.sacp.course.core.mapper.CourseResourceMapper;
import com.sacp.course.core.mapper.CourseVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseResourcesRepository {
    @Autowired
    private CourseVideoMapper courseVideoMapper;

    @Autowired
    private CourseResourceMapper courseResourceMapper;

    //video============================================================
    public boolean insertCourseVideo(CourseVideo courseVideo){
        int i = courseVideoMapper.insertSelective(courseVideo);
        return i==1?true:false;
    }

    public List<CourseVideo> getByCourseIdAndPage(Integer courseId,int pagesize,int currentPage){
        int start = (currentPage-1)*pagesize;
        return courseVideoMapper.selectByCourseIdAndPage(courseId,start,pagesize);
    }

    public long getVideoCountByCourseId(Integer id){
        CourseVideoExample example = new CourseVideoExample();
        example.createCriteria().andCourseIdEqualTo(id).andIsDeleteEqualTo(0);
        return courseVideoMapper.countByExample(example);
    }

    public boolean deleteVideoById(Integer videoId){
        CourseVideoExample example = new CourseVideoExample();
        example.createCriteria().andIdEqualTo(videoId);
        CourseVideo video = new CourseVideo();
        video.setIsDelete(1);
        int i = courseVideoMapper.updateByExampleSelective(video,example);
        return i==1?true:false;
    }

    public List<CourseVideo> getAllVideoByCourseId(Integer courseId){
        CourseVideoExample example = new CourseVideoExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andIsDeleteEqualTo(0);
        example.setOrderByClause("orders desc");
        return courseVideoMapper.selectByExample(example);
    }
}
