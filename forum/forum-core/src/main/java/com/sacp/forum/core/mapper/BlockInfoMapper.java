package com.sacp.forum.core.mapper;

import com.sacp.forum.core.entity.BlockInfo;
import com.sacp.forum.core.entity.BlockInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlockInfoMapper {
    long countByExample(BlockInfoExample example);

    int deleteByExample(BlockInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlockInfo record);

    int insertSelective(BlockInfo record);

    List<BlockInfo> selectByExample(BlockInfoExample example);

    BlockInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlockInfo record, @Param("example") BlockInfoExample example);

    int updateByExample(@Param("record") BlockInfo record, @Param("example") BlockInfoExample example);

    int updateByPrimaryKeySelective(BlockInfo record);

    int updateByPrimaryKey(BlockInfo record);
}