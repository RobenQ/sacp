package com.sacp.forum.core.mapper;

import com.sacp.forum.core.entity.Post;
import com.sacp.forum.core.entity.PostExample;
import com.sacp.forum.core.entity.PostWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    long countByExample(PostExample example);

    int deleteByExample(PostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PostWithBLOBs record);

    int insertSelective(PostWithBLOBs record);

    List<PostWithBLOBs> selectByExampleWithBLOBs(PostExample example);

    List<Post> selectByExample(PostExample example);

    PostWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PostWithBLOBs record, @Param("example") PostExample example);

    int updateByExampleWithBLOBs(@Param("record") PostWithBLOBs record, @Param("example") PostExample example);

    int updateByExample(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPrimaryKeySelective(PostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PostWithBLOBs record);

    int updateByPrimaryKey(Post record);

    List<Post> getPostTop5(@Param("blockId") Integer blockId);

    List<Post> getPostTop5BySacpId(@Param("sacpId") String sacpId);

    List<Post> getPostByPage(@Param("blockId")Integer blockId,@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    List<Post> getPostByPage2(@Param("sacpId")String sacpId,@Param("start") Integer start,@Param("pageSize") Integer pageSize);
}