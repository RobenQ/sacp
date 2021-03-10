package com.sacp.permission.core.repository;

import com.sacp.permission.client.response.MemberRoleResponse;
import com.sacp.permission.core.entity.MemberRole;
import com.sacp.permission.core.entity.MemberRoleExample;
import com.sacp.permission.core.mapper.MemberRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MemberRoleRepository {

    @Autowired
    private MemberRoleMapper memberRoleMapper;

    public int getRoleIdBySacpId(String sacpId){
        MemberRoleExample example = new MemberRoleExample();
        example.createCriteria().andSacpIdEqualTo(sacpId);
        MemberRole memberRole = memberRoleMapper.selectByExample(example).get(0);
        return memberRole.getRoleId();
    }

    public List<MemberRole> getByRoleId(Integer id){
        MemberRoleExample example = new MemberRoleExample();
        example.createCriteria().andRoleIdEqualTo(id);
        return memberRoleMapper.selectByExample(example);
    }

    public List<MemberRole> getAllMemberRole(){
        MemberRoleExample example = new MemberRoleExample();
        example.createCriteria().andIdIsNotNull();
        return memberRoleMapper.selectByExample(example);
    }

    public boolean updateMemberRole(MemberRole memberRole,String sacpId){
        MemberRoleExample example = new MemberRoleExample();
        example.createCriteria().andSacpIdEqualTo(sacpId);
        int i = memberRoleMapper.updateByExampleSelective(memberRole, example);
        return i==1?true:false;
    }
}
