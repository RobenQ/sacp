package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.Discussion;
import com.sacp.course.core.entity.DiscussionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscussionMapper {
    long countByExample(DiscussionExample example);

    int deleteByExample(DiscussionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Discussion record);

    int insertSelective(Discussion record);

    List<Discussion> selectByExampleWithBLOBs(DiscussionExample example);

    List<Discussion> selectByExample(DiscussionExample example);

    Discussion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Discussion record, @Param("example") DiscussionExample example);

    int updateByExampleWithBLOBs(@Param("record") Discussion record, @Param("example") DiscussionExample example);

    int updateByExample(@Param("record") Discussion record, @Param("example") DiscussionExample example);

    int updateByPrimaryKeySelective(Discussion record);

    int updateByPrimaryKeyWithBLOBs(Discussion record);

    int updateByPrimaryKey(Discussion record);
}