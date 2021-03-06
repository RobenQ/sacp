package com.sacp.permission.core.repository;

import com.sacp.permission.core.entity.MemberRole;
import com.sacp.permission.core.entity.MemberRoleExample;
import com.sacp.permission.core.entity.Role;
import com.sacp.permission.core.entity.RoleExample;
import com.sacp.permission.core.mapper.MemberRoleMapper;
import com.sacp.permission.core.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class RoleRepository {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MemberRoleMapper memberRoleMapper;

    //获取所有角色
    public List<Role> getAllRole(){
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIsNotNull();
        return roleMapper.selectByExample(example);
    }

    //根据id获取某一个角色
    public Role getRoleById(int id){
        return roleMapper.selectByPrimaryKey(id);
    }

    //根据用户的sacpId获取角色
    public List<MemberRole> getRolesBySacpId(String sacpId){
        MemberRoleExample example = new MemberRoleExample();
        example.createCriteria().andSacpIdEqualTo(sacpId);
        return memberRoleMapper.selectByExample(example);
    }

    //增加一个角色
    @Transactional(rollbackFor = Exception.class)
    public Role insertRole(String roleName,String expression){
        Role role = new Role();
        role.setRoleName(roleName);
        role.setExpression(expression);
        role.setCreateTime(new Date());
        roleMapper.insertSelective(role);
        return roleMapper.selectByPrimaryKey(role.getId());
    }

    //更新一个角色的信息
    @Transactional(rollbackFor = Exception.class)
    public Role updateRole(Integer id,String roleName,String expression){
        Role role  =new Role();
        role.setId(id);
        role.setRoleName(roleName);
        role.setExpression(expression);
        role.setModifyTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
        return roleMapper.selectByPrimaryKey(id);
    }

    //删除一个角色
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(Integer id){
        int i = roleMapper.deleteByPrimaryKey(id);
        if (i==1)
            return true;
        else
            return false;
    }
}
