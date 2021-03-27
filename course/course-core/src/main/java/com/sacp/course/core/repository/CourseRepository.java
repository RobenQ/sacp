package com.sacp.course.core.repository;

import com.sacp.course.core.entity.CourseInfo;
import com.sacp.course.core.entity.CourseInfoExample;
import com.sacp.course.core.mapper.CourseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseRepository {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    public List<CourseInfo> getAllCourse(){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andIdIsNotNull().andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseInfoMapper.selectByExample(example);
    }

    public List<CourseInfo> getCourseBySacpId(String sacpId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseInfoMapper.selectByExample(example);
    }

    public List<CourseInfo> getCourseByClassifyId(Integer classifyId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andClassifyIdEqualTo(classifyId).andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseInfoMapper.selectByExample(example);
    }

    public CourseInfo getCourseById(Integer id){
        CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(id);
        return courseInfo;
    }

    public boolean insertCourse(CourseInfo courseInfo){
        int i = courseInfoMapper.insertSelective(courseInfo);
        return i==1?true:false;
    }

    public long countBySacpId(String sacpId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andIsDeleteEqualTo(0);
        long l = courseInfoMapper.countByExample(example);
        return l;
    }

    public long countByClassifyId(Integer classifyId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andClassifyIdEqualTo(classifyId).andIsDeleteEqualTo(0);
        long l = courseInfoMapper.countByExample(example);
        return l;
    }

    public List<CourseInfo> getCourseByPage(String sacpId,int pagesize,int currentPage){
        int start = (currentPage-1)*pagesize;
        return courseInfoMapper.selectByPage(sacpId,start,pagesize);
    }

    public List<CourseInfo> getCourseByPage(Integer classifyId,int pagesize,int currentPage){
        int start = (currentPage-1)*pagesize;
        return courseInfoMapper.selectByClassifyAndPage(classifyId,start,pagesize);
    }
}
