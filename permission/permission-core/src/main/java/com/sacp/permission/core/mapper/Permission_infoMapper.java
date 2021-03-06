package com.sacp.permission.core.mapper;

import com.sacp.permission.core.entity.Permission_info;
import com.sacp.permission.core.entity.Permission_infoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Permission_infoMapper {
    long countByExample(Permission_infoExample example);

    int deleteByExample(Permission_infoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission_info record);

    int insertSelective(Permission_info record);

    List<Permission_info> selectByExample(Permission_infoExample example);

    Permission_info selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission_info record, @Param("example") Permission_infoExample example);

    int updateByExample(@Param("record") Permission_info record, @Param("example") Permission_infoExample example);

    int updateByPrimaryKeySelective(Permission_info record);

    int updateByPrimaryKey(Permission_info record);
}