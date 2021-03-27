package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.CourseInfo;
import com.sacp.course.core.entity.CourseVideo;
import com.sacp.course.core.entity.CourseVideoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseVideoMapper {
    long countByExample(CourseVideoExample example);

    int deleteByExample(CourseVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseVideo record);

    int insertSelective(CourseVideo record);

    List<CourseVideo> selectByExample(CourseVideoExample example);

    CourseVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseVideo record, @Param("example") CourseVideoExample example);

    int updateByExample(@Param("record") CourseVideo record, @Param("example") CourseVideoExample example);

    int updateByPrimaryKeySelective(CourseVideo record);

    int updateByPrimaryKey(CourseVideo record);

    List<CourseVideo> selectByCourseIdAndPage(@Param("courseId")Integer courseId, @Param("start") int start, @Param("pageSize") int pageSize);
}