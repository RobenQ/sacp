package com.sacp.permission.client.api;

import com.sacp.permission.client.request.MemberRoleRequest;
import com.sacp.permission.client.request.RolesRequest;
import com.sacp.permission.client.request.ChangeRolePermissionRequest;
import com.sacp.permission.client.response.ChangeRolePermissionResponse;
import com.sacp.permission.client.response.MemberRoleResponse;
import com.sacp.permission.client.response.RolesResponse;

import java.util.List;

public interface RoleApi {

    public List<String> getRolesBySacpId(String sacpId);
    public RolesResponse getRoleBySacpId(String sacpId);
    public List<RolesResponse> getAllRole();
    public RolesResponse getRoleById(Integer id);
    public RolesResponse addRole(RolesRequest request);
    public RolesResponse updateRole(RolesRequest request);
    public boolean deleteRole(Integer id);
    public ChangeRolePermissionResponse getRolePermissionByRoleId(Integer roleId);
    public boolean updateRolePermission(ChangeRolePermissionRequest request);
    public List<MemberRoleResponse> getMemberRoleByRoleId(Integer id);
    public List<MemberRoleResponse> getAllMemberRole();
    public boolean updateMemberRole(MemberRoleRequest request);

}
