package com.sacp.forum.core.mapper;

import com.sacp.forum.core.entity.ReplyLikes;
import com.sacp.forum.core.entity.ReplyLikesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyLikesMapper {
    long countByExample(ReplyLikesExample example);

    int deleteByExample(ReplyLikesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReplyLikes record);

    int insertSelective(ReplyLikes record);

    List<ReplyLikes> selectByExample(ReplyLikesExample example);

    ReplyLikes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReplyLikes record, @Param("example") ReplyLikesExample example);

    int updateByExample(@Param("record") ReplyLikes record, @Param("example") ReplyLikesExample example);

    int updateByPrimaryKeySelective(ReplyLikes record);

    int updateByPrimaryKey(ReplyLikes record);
}