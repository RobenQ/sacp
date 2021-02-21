package com.sacp.member.core.mapper;

import com.sacp.member.core.entity.AccountClassify;
import com.sacp.member.core.entity.AccountClassifyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountClassifyMapper {
    long countByExample(AccountClassifyExample example);

    int deleteByExample(AccountClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountClassify record);

    int insertSelective(AccountClassify record);

    List<AccountClassify> selectByExample(AccountClassifyExample example);

    AccountClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountClassify record, @Param("example") AccountClassifyExample example);

    int updateByExample(@Param("record") AccountClassify record, @Param("example") AccountClassifyExample example);

    int updateByPrimaryKeySelective(AccountClassify record);

    int updateByPrimaryKey(AccountClassify record);
}