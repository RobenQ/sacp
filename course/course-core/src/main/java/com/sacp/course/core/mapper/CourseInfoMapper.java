package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.CourseInfo;
import com.sacp.course.core.entity.CourseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseInfoMapper {
    long countByExample(CourseInfoExample example);

    int deleteByExample(CourseInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    List<CourseInfo> selectByExample(CourseInfoExample example);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByExample(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
}