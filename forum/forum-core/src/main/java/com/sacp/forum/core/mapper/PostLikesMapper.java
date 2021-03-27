package com.sacp.forum.core.mapper;

import com.sacp.forum.core.entity.PostLikes;
import com.sacp.forum.core.entity.PostLikesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostLikesMapper {
    long countByExample(PostLikesExample example);

    int deleteByExample(PostLikesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PostLikes record);

    int insertSelective(PostLikes record);

    List<PostLikes> selectByExample(PostLikesExample example);

    PostLikes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PostLikes record, @Param("example") PostLikesExample example);

    int updateByExample(@Param("record") PostLikes record, @Param("example") PostLikesExample example);

    int updateByPrimaryKeySelective(PostLikes record);

    int updateByPrimaryKey(PostLikes record);
}