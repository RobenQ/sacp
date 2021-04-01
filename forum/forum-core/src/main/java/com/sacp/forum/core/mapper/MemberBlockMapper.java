package com.sacp.forum.core.mapper;

import com.sacp.forum.core.entity.MemberBlock;
import com.sacp.forum.core.entity.MemberBlockExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberBlockMapper {
    long countByExample(MemberBlockExample example);

    int deleteByExample(MemberBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberBlock record);

    int insertSelective(MemberBlock record);

    List<MemberBlock> selectByExample(MemberBlockExample example);

    MemberBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberBlock record, @Param("example") MemberBlockExample example);

    int updateByExample(@Param("record") MemberBlock record, @Param("example") MemberBlockExample example);

    int updateByPrimaryKeySelective(MemberBlock record);

    int updateByPrimaryKey(MemberBlock record);

    List<MemberBlock> getMbBySacpIdLimit(@Param("sacpId") String sacpId);
}