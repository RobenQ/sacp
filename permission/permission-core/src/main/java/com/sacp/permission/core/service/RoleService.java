package com.sacp.permission.core.service;

import com.alibaba.fastjson.JSON;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.permission.client.request.ChangeRolePermissionRequest;
import com.sacp.permission.client.request.MemberRoleRequest;
import com.sacp.permission.client.request.RolesRequest;
import com.sacp.permission.client.response.*;
import com.sacp.permission.core.entity.*;
import com.sacp.permission.core.repository.MemberRoleRepository;
import com.sacp.permission.core.repository.PermissionRespository;
import com.sacp.permission.core.repository.RolePermissionRepository;
import com.sacp.permission.core.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@DubboService(version = "1.0")
public class RoleService implements RoleApi {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRespository permissionRespository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public boolean updateRolePermission(ChangeRolePermissionRequest request) {
        List<Integer> permissionId = request.getPermissionId();
        int roleId = request.getRoleId();
        //权限列表为空，删除所有权限
        if (permissionId.size()==0){
            rolePermissionRepository.deleteAllPermissionByRoleId(roleId);
            return true;
        } else {
            //获取已有的权限ID集合
            List<PermissionResponse> permissions = rolePermissionRepository.getPermissonsByRoleId(roleId).getPermissions();
            Set<Integer> hava = new HashSet<>(permissions.size());
            Set<Integer> hava2 = new HashSet<>(permissions.size());
            for (PermissionResponse response:permissions) {
                hava.add(response.getId());
                hava2.add(response.getId());
            }
            //获取修改后的权限ID集合
            Set<Integer> news = new HashSet<>(permissionId);
            //求差集，删除差集中的权限
            hava.removeAll(news);
            boolean f1 = rolePermissionRepository.deleteByPermissionIdList(hava, roleId);
            //求差集，新增差集中的权限
            news.removeAll(hava2);
            boolean f2 = rolePermissionRepository.addByPermissionIdList(news, roleId);
            return f1 && f2;
        }
    }

    @Override
    public List<MemberRoleResponse> getMemberRoleByRoleId(Integer id) {
        List<MemberRole> byRoleId = memberRoleRepository.getByRoleId(id);
        List<MemberRoleResponse> responses = new ArrayList<>(byRoleId.size());
        for (MemberRole memberRole:byRoleId) {
            MemberRoleResponse memberRoleResponse = new MemberRoleResponse();
            BeanUtils.copyProperties(memberRole,memberRoleResponse);
            responses.add(memberRoleResponse);
        }
        return responses;
    }

    @Override
    public List<MemberRoleResponse> getAllMemberRole() {
        List<MemberRole> byRoleId = memberRoleRepository.getAllMemberRole();
        List<MemberRoleResponse> responses = new ArrayList<>(byRoleId.size());
        for (MemberRole memberRole:byRoleId) {
            MemberRoleResponse memberRoleResponse = new MemberRoleResponse();
            BeanUtils.copyProperties(memberRole,memberRoleResponse);
            responses.add(memberRoleResponse);
        }
        return responses;
    }

    @Override
    public boolean updateMemberRole(MemberRoleRequest request) {
        MemberRole memberRole = new MemberRole();
        memberRole.setRoleId(request.getRoleId());
        return memberRoleRepository.updateMemberRole(memberRole,request.getSacpId());
    }

    @Override
    public boolean addMemberRole(MemberRoleRequest request) {
        MemberRole memberRole = new MemberRole();
        BeanUtils.copyProperties(request,memberRole);
        return memberRoleRepository.insertMemberRole(memberRole);
    }

    @Override
    public List<String> getRolesBySacpId(String sacpId) {
        List<String> roles = new ArrayList<>(2);
        List<MemberRole> rolesBySacpId = roleRepository.getRolesBySacpId(sacpId);
        if (rolesBySacpId.size()>0){
            int memberRoleId = roleRepository.getRolesBySacpId(sacpId).get(0).getRoleId();
            roles.add(roleRepository.getRoleById(memberRoleId).getExpression());
        }
        return roles;
    }

    @Override
    public RolesResponse getRoleBySacpId(String sacpId) {
        int roleId = memberRoleRepository.getRoleIdBySacpId(sacpId);
        Role role = roleRepository.getRoleById(roleId);
        RolesResponse response = new RolesResponse();
        BeanUtils.copyProperties(role,response);
        return response;
    }

    @Override
    public ChangeRolePermissionResponse getRolePermissionByRoleId(Integer roleId) {
        ChangeRolePermissionResponse response = new ChangeRolePermissionResponse();
        //获取所有权限ID列表
        List<Permission_info> allPermission = permissionRespository.getAllPermission();
        Set<Integer> all = new HashSet<>(allPermission.size());
        for (Permission_info info:allPermission) {
            all.add(info.getId());
        }
        //获取已有权限ID列表
        List<PermissionResponse> permissions = rolePermissionRepository.getPermissonsByRoleId(roleId).getPermissions();
        Set<Integer> have = new HashSet<>(permissions.size());
        for (PermissionResponse info:permissions) {
            have.add(info.getId());
        }
        //获取没有权限的ID列表
        all.removeAll(have);
        Set<Integer> no = all;

        //获取对应的权限信息
        response.setHave(permissionRespository.getPermissionByIdList(have));
        response.setNo(permissionRespository.getPermissionByIdList(no));
        return response;
    }

    @Override
    public List<RolesResponse> getAllRole() {
        List<Role> roles = roleRepository.getAllRole();
        List<RolesResponse> roleNameList = new ArrayList<>();
        for (Role role:roles) {
            RolesResponse response = new RolesResponse();
            response.setId(role.getId());
            response.setRoleName(role.getRoleName());
            response.setExpression(role.getExpression());
            response.setCreateTime(role.getCreateTime());
            response.setModifyTime(role.getModifyTime());
            roleNameList.add(response);
        }
        log.info(JSON.toJSONString(roleNameList));
        return roleNameList;
    }

    @Override
    public RolesResponse getRoleById(Integer id) {
        Role roleById = roleRepository.getRoleById(id);
        RolesResponse response = new RolesResponse();
        BeanUtils.copyProperties(roleById,response);
        return response;
    }

    @Override
    public RolesResponse addRole(RolesRequest request) {
        Role role = roleRepository.insertRole(request.getRoleName(),request.getExpression());
        RolesResponse rolesResponse = new RolesResponse();
        BeanUtils.copyProperties(role,rolesResponse);
        return rolesResponse;
    }

    @Override
    public RolesResponse updateRole(RolesRequest request) {
        Role role = roleRepository.updateRole(request.getId(),request.getRoleName(),request.getExpression());
        RolesResponse rolesResponse = new RolesResponse();
        BeanUtils.copyProperties(role,rolesResponse);
        return rolesResponse;
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleRepository.deleteRole(id);
    }
}
