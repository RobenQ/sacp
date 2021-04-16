package com.sacp.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.admin.response.UserAndRoleResponse;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.permission.client.response.MemberRoleResponse;
import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.permission.client.request.MemberRoleRequest;
import com.sacp.permission.client.request.PermissionRequest;
import com.sacp.permission.client.request.RolesRequest;
import com.sacp.permission.client.request.ChangeRolePermissionRequest;
import com.sacp.permission.client.response.ChangeRolePermissionResponse;
import com.sacp.permission.client.response.PermissionResponse;
import com.sacp.permission.client.response.RolesResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {

    @DubboReference(version = "1.0")
    private RoleApi roleApi;

    @DubboReference(version = "1.0")
    private PermissionApi permissionApi;

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    //获取所有角色信息
    @RequestMapping("getallrole")
    public AdminResponse<List<RolesResponse>> getAllRole() {
        AdminResponse<List<RolesResponse>> response = new AdminResponse<>();
        response.setCode(200);
        response.setResult(roleApi.getAllRole());
        return response;
    }

    //添加角色
    @PostMapping("addrole")
    public AdminResponse<Object> addRole(@RequestBody JSONObject role){
        RolesRequest request = new RolesRequest();
        request.setRoleName(role.getString("roleName"));
        request.setExpression(role.getString("expression"));
        RolesResponse response = roleApi.addRole(request);
        return AdminResponse.buildSuccess(response);
    }

    //更新角色信息
    @PostMapping("updaterole")
    public AdminResponse<Object> updateRole(@RequestBody JSONObject role){
        RolesRequest request = new RolesRequest();
        request.setId(role.getInteger("id"));
        request.setRoleName(role.getString("roleName"));
        request.setExpression(role.getString("expression"));
        RolesResponse response = roleApi.updateRole(request);
        return AdminResponse.buildSuccess(response);
    }

    //删除一个角色
    @RequestMapping("deleterole")
    public AdminResponse<Object> deleteRole(@RequestParam(name = "id") int roleId){
        roleApi.deleteRole(roleId);
        return AdminResponse.buildSuccess();
    }

    //获取所有权限信息
    @RequestMapping("getallpermission")
    public AdminResponse<List<PermissionResponse>> getAllPermission() {
        AdminResponse<List<PermissionResponse>> response = new AdminResponse<>();
        response.setCode(200);
        response.setResult(permissionApi.getAllPermission());
        return response;
    }

    //添加权限
    @PostMapping("addpermission")
    public AdminResponse<Object> addPermission(@RequestBody JSONObject permission){
        PermissionRequest request = new PermissionRequest();
        request.setPermissionName(permission.getString("permissionName"));
        request.setExpression(permission.getString("expression"));
        PermissionResponse response = permissionApi.addPermission(request);
        return AdminResponse.buildSuccess(response);
    }

    //更新权限信息
    @PostMapping("updatepromission")
    public AdminResponse<Object> updatePromission(@RequestBody JSONObject permission){
        PermissionRequest request = new PermissionRequest();
        request.setId(permission.getInteger("id"));
        request.setPermissionName(permission.getString("permissionName"));
        request.setExpression(permission.getString("expression"));
        PermissionResponse response = permissionApi.updatePermission(request);
        return AdminResponse.buildSuccess(response);
    }

    //删除一个权限
    @GetMapping("deletepermission")
    public AdminResponse<Object> deletePermission(@RequestParam(name = "id") int permissionId){
        permissionApi.deletePermission(permissionId);
        return AdminResponse.buildSuccess();
    }

    //获取某个角色的全部权限
    @GetMapping("getrolepermission")
    public AdminResponse<Object> getRolePermission(@RequestParam(name = "roleId") int roleId){
        AdminResponse<ChangeRolePermissionResponse> response = new AdminResponse<>();
        response.setCode(200);
        response.setResult(roleApi.getRolePermissionByRoleId(roleId));
        return AdminResponse.buildSuccess(response);
    }

    //修改某个角色的全部权限
    @PostMapping("confirmrolepermission")
    public AdminResponse<Object> confirmRolePermission(@RequestBody JSONObject jsonObject){
        List<Integer> permissionList = (List<Integer>)(jsonObject.get("permissions"));
        Integer roleId = jsonObject.getInteger("roleId");
        if (roleId==0 || roleId==null)
            return AdminResponse.buildFaild("roleId为空");
        ChangeRolePermissionRequest request = new ChangeRolePermissionRequest();
        request.setPermissionId(permissionList);
        request.setRoleId(roleId);
        return roleApi.updateRolePermission(request)?AdminResponse.buildSuccess():AdminResponse.buildFaild("修改失败");
    }

    //按条件获取所有的用户及其角色信息
    @PostMapping("getmemberandrole")
    public AdminResponse<Object> getMemberAndRole(@RequestBody MemberRoleRequest request){
        if (request.getSacpId()!=null){
            MemberRequest memberRequest = new MemberRequest();
            memberRequest.setSacpId(request.getSacpId());
            List<MemberResponse> account = memberApi.getAccount(memberRequest);

            List<UserAndRoleResponse> response = new ArrayList<>(account.size());
            for (MemberResponse member:account) {
                UserAndRoleResponse userAndRoleResponse = new UserAndRoleResponse();
                RolesResponse role = roleApi.getRoleBySacpId(member.getSacpId());
                userAndRoleResponse.setMember(member);
                userAndRoleResponse.setRole(role);
                response.add(userAndRoleResponse);
            }
            return AdminResponse.buildSuccess(response);
        }else if (request.getRoleId()!=null){
            List<MemberRoleResponse> memberRoleByRoleId = roleApi.getMemberRoleByRoleId(request.getRoleId());

            List<UserAndRoleResponse> response = new ArrayList<>(memberRoleByRoleId.size());
            for (MemberRoleResponse memberRole:memberRoleByRoleId) {
                UserAndRoleResponse userAndRoleResponse = new UserAndRoleResponse();
                userAndRoleResponse.setRole(roleApi.getRoleById(memberRole.getRoleId()));
                MemberRequest memberRequest = new MemberRequest();
                memberRequest.setSacpId(request.getSacpId());
                userAndRoleResponse.setMember(memberApi.getAccount(memberRequest).get(0));
                response.add(userAndRoleResponse);
            }
            return AdminResponse.buildSuccess(response);
        }else{
            List<MemberRoleResponse> memberRoleByRoleId = roleApi.getAllMemberRole();

            List<UserAndRoleResponse> response = new ArrayList<>(memberRoleByRoleId.size());
            for (MemberRoleResponse memberRole:memberRoleByRoleId) {
                UserAndRoleResponse userAndRoleResponse = new UserAndRoleResponse();
                userAndRoleResponse.setRole(roleApi.getRoleById(memberRole.getRoleId()));
                MemberRequest memberRequest = new MemberRequest();
                memberRequest.setSacpId(memberRole.getSacpId());
                if (memberApi.getAccount(memberRequest).size()!=0){
                    userAndRoleResponse.setMember(memberApi.getAccount(memberRequest).get(0));
                    response.add(userAndRoleResponse);
                }
            }
            return AdminResponse.buildSuccess(response);
        }
    }

    //修改某个用户的角色
    @PostMapping("changeuserrole")
    public AdminResponse<Object> changeUserRole(@RequestBody MemberRoleRequest request){
        boolean b = roleApi.updateMemberRole(request);
        return AdminResponse.buildSuccess(b);
    }

}
