package com.sacp.course.core.mapper;

import com.sacp.course.core.entity.DiscussionLikes;
import com.sacp.course.core.entity.DiscussionLikesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscussionLikesMapper {
    long countByExample(DiscussionLikesExample example);

    int deleteByExample(DiscussionLikesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DiscussionLikes record);

    int insertSelective(DiscussionLikes record);

    List<DiscussionLikes> selectByExample(DiscussionLikesExample example);

    DiscussionLikes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DiscussionLikes record, @Param("example") DiscussionLikesExample example);

    int updateByExample(@Param("record") DiscussionLikes record, @Param("example") DiscussionLikesExample example);

    int updateByPrimaryKeySelective(DiscussionLikes record);

    int updateByPrimaryKey(DiscussionLikes record);
}