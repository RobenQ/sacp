package com.sacp.course.core.repository;

import com.alibaba.fastjson.JSON;
import com.sacp.course.core.entity.CourseClassify;
import com.sacp.course.core.entity.CourseClassifyExample;
import com.sacp.course.core.mapper.CourseClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseClassifyRepository {
    @Autowired
    private CourseClassifyMapper courseClassifyMapper;

    public List<CourseClassify> getAllClassify(){
        CourseClassifyExample example = new CourseClassifyExample();
        example.createCriteria().andIdIsNotNull();
        return courseClassifyMapper.selectByExample(example);
    }

    public List<CourseClassify> getClassifyById(Integer classifyId){
        CourseClassifyExample example = new CourseClassifyExample();
        example.createCriteria().andIdEqualTo(classifyId);
        return courseClassifyMapper.selectByExample(example);
    }

    public List<CourseClassify> getClassifyByName(String classifyName){
        CourseClassifyExample example = new CourseClassifyExample();
        example.createCriteria().andClassifyNameEqualTo(classifyName);
        return courseClassifyMapper.selectByExample(example);
    }

    public boolean insertCourseClassify(CourseClassify courseClassify){
        if (getClassifyByName(courseClassify.getClassifyName()).size()!=0)
            return false;
        int i = courseClassifyMapper.insertSelective(courseClassify);
        return i==1?true:false;
    }

    public boolean updateClassify(CourseClassify classify){
        CourseClassifyExample example = new CourseClassifyExample();
        example.createCriteria().andIdEqualTo(classify.getId());
        int i = courseClassifyMapper.updateByExampleSelective(classify,example);
        return i==1?true:false;
    }

    public List<CourseClassify> getBySeletive(CourseClassify classify){
        CourseClassifyExample example = new CourseClassifyExample();
        CourseClassifyExample.Criteria criteria = example.createCriteria();
        if (classify.getId()!=null&&classify.getId()!=0)
            criteria.andIdEqualTo(classify.getId());
        if (classify.getDescr()!=null)
            criteria.andDescEqualTo(classify.getDescr());
        if (classify.getClassifyName()!=null)
            criteria.andClassifyNameEqualTo(classify.getClassifyName());
        List<CourseClassify> courseClassifies = courseClassifyMapper.selectByExample(example);
        return courseClassifies;
    }
}
