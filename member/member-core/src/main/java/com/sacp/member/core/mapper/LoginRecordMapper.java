package com.sacp.member.core.mapper;

import com.sacp.member.core.entity.LoginRecord;
import com.sacp.member.core.entity.LoginRecordExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    List<LoginRecord> selectByExample(LoginRecordExample example);

    LoginRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
}