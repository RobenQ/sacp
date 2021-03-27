package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.CourseClassify;
import com.sacp.course.core.entity.CourseClassifyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseClassifyMapper {
    long countByExample(CourseClassifyExample example);

    int deleteByExample(CourseClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseClassify record);

    int insertSelective(CourseClassify record);

    List<CourseClassify> selectByExample(CourseClassifyExample example);

    CourseClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseClassify record, @Param("example") CourseClassifyExample example);

    int updateByExample(@Param("record") CourseClassify record, @Param("example") CourseClassifyExample example);

    int updateByPrimaryKeySelective(CourseClassify record);

    int updateByPrimaryKey(CourseClassify record);
}