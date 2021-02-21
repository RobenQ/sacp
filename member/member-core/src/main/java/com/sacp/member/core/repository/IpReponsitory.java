package com.sacp.member.core.repository;

import com.sacp.member.core.entity.LoginRecord;
import com.sacp.member.core.mapper.LoginRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IpReponsitory {

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    public void insertLoginRecorde(LoginRecord loginRecord){
        loginRecordMapper.insertSelective(loginRecord);
    }
}
