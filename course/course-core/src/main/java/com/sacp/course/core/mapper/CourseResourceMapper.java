package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.CourseResource;
import com.sacp.course.core.entity.CourseResourceExample;
import java.util.List;

import com.sacp.course.core.entity.CourseVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseResourceMapper {
    long countByExample(CourseResourceExample example);

    int deleteByExample(CourseResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseResource record);

    int insertSelective(CourseResource record);

    List<CourseResource> selectByExample(CourseResourceExample example);

    CourseResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseResource record, @Param("example") CourseResourceExample example);

    int updateByExample(@Param("record") CourseResource record, @Param("example") CourseResourceExample example);

    int updateByPrimaryKeySelective(CourseResource record);

    int updateByPrimaryKey(CourseResource record);

    List<CourseResource> selectByCourseIdAndPage(@Param("courseId")Integer courseId, @Param("start") int start, @Param("pageSize") int pageSize);
}