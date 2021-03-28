package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.MemberCourse;
import com.sacp.course.core.entity.MemberCourseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberCourseMapper {
    long countByExample(MemberCourseExample example);

    int deleteByExample(MemberCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCourse record);

    int insertSelective(MemberCourse record);

    List<MemberCourse> selectByExample(MemberCourseExample example);

    MemberCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCourse record, @Param("example") MemberCourseExample example);

    int updateByExample(@Param("record") MemberCourse record, @Param("example") MemberCourseExample example);

    int updateByPrimaryKeySelective(MemberCourse record);

    int updateByPrimaryKey(MemberCourse record);
}