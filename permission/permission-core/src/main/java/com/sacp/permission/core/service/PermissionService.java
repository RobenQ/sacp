package com.sacp.permission.core.service;

import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.request.PermissionRequest;
import com.sacp.permission.client.response.PermissionResponse;
import com.sacp.permission.core.entity.Permission_info;
import com.sacp.permission.core.repository.MemberRoleRepository;
import com.sacp.permission.core.repository.PermissionRespository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0")
public class PermissionService implements PermissionApi {

    @Autowired
    private PermissionRespository permissionRespository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public List<String> getPermissionBySacpId(String sacpId) {
        List<String> permissions = new ArrayList<>();
        int roleId = memberRoleRepository.getRoleIdBySacpId(sacpId);
        List<Permission_info> permissionsByRoleId = permissionRespository.getPermissionsByRoleId(roleId);
        List<String> permissionStringList = new ArrayList<>(permissionsByRoleId.size());
        for (Permission_info info:permissionsByRoleId) {
            permissionStringList.add(info.getExpression());
        }
        return permissionStringList;
    }

    @Override
    public List<PermissionResponse> getAllPermission() {
        List<Permission_info> allPermission = permissionRespository.getAllPermission();
        List<PermissionResponse> responses = new ArrayList<>(allPermission.size());
        for (Permission_info info:allPermission) {
            PermissionResponse response = new PermissionResponse();
            BeanUtils.copyProperties(info,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public PermissionResponse addPermission(PermissionRequest request) {
        Permission_info info = new Permission_info();
        info.setPermissionName(request.getPermissionName());
        info.setExpression(request.getExpression());
        info = permissionRespository.insertPermission(info);
        PermissionResponse response = new PermissionResponse();
        BeanUtils.copyProperties(info,response);
        return response;
    }

    @Override
    public PermissionResponse updatePermission(PermissionRequest request) {
        Permission_info info = new Permission_info();
        BeanUtils.copyProperties(request,info);
        Permission_info permissionInfo = permissionRespository.updatePermission(info);
        PermissionResponse response = new PermissionResponse();
        BeanUtils.copyProperties(permissionInfo,response);
        return response;
    }

    @Override
    public boolean deletePermission(Integer id) {
        return permissionRespository.deletePermission(id);
    }
}
