package com.sacp.permission.core.repository;

import com.sacp.permission.client.response.ChangeRolePermissionResponse;
import com.sacp.permission.client.response.PermissionResponse;
import com.sacp.permission.client.response.RolePermissionResponse;
import com.sacp.permission.client.response.RolesResponse;
import com.sacp.permission.core.entity.Permission_info;
import com.sacp.permission.core.entity.Role;
import com.sacp.permission.core.entity.RolePermission;
import com.sacp.permission.core.entity.RolePermissionExample;
import com.sacp.permission.core.mapper.RolePermissionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class RolePermissionRepository {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRespository permissionRespository;

    //获取某个角色的所有权限
    public RolePermissionResponse getPermissonsByRoleId(Integer roleId){
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);

        RolePermissionResponse rolePermissionResponse = new RolePermissionResponse();
        //获取所有权限
        rolePermissionResponse.setPermissions(new ArrayList<>(rolePermissions.size()));
        for (RolePermission rolePermisson:rolePermissions) {
            Permission_info permissonById = permissionRespository.getPermissonById(rolePermisson.getPermissionId());
            PermissionResponse permissionResponse = new PermissionResponse();
            BeanUtils.copyProperties(permissonById,permissionResponse);
            rolePermissionResponse.getPermissions().add(permissionResponse);
        }
        //获取角色信息
        Role role = roleRepository.getRoleById(roleId);
        RolesResponse rolesResponse = new RolesResponse();
        BeanUtils.copyProperties(role,rolesResponse);
        
        rolePermissionResponse.setRole(rolesResponse);

        return rolePermissionResponse;
    }

    //删除某个角色的所有权限
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAllPermissionByRoleId(Integer roleId){
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        try {
            rolePermissionMapper.deleteByExample(example);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //批量删除角色权限
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByPermissionIdList(Set<Integer> permissions,Integer roleId){
        try {
            for (Integer i:permissions) {
                RolePermissionExample example = new RolePermissionExample();
                example.createCriteria().andRoleIdEqualTo(roleId).andPermissionIdEqualTo(i);
                rolePermissionMapper.deleteByExample(example);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //批量增加角色权限
    @Transactional(rollbackFor = Exception.class)
    public boolean addByPermissionIdList(Set<Integer> permissions,Integer roleId){
        try {
            for (Integer id:permissions) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(id);
                rolePermission.setCreateTime(new Date());
                rolePermissionMapper.insertSelective(rolePermission);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    
}
