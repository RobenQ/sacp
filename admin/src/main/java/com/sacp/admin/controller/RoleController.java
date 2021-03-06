package com.sacp.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.permission.client.request.PermissionRequest;
import com.sacp.permission.client.request.RolesRequest;
import com.sacp.permission.client.request.ChangeRolePermissionRequest;
import com.sacp.permission.client.response.ChangeRolePermissionResponse;
import com.sacp.permission.client.response.PermissionResponse;
import com.sacp.permission.client.response.RolesResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @DubboReference(version = "1.0")
    private RoleApi roleApi;

    @DubboReference(version = "1.0")
    private PermissionApi permissionApi;

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
    @RequestMapping("deletepermission")
    public AdminResponse<Object> deletePermission(@RequestParam(name = "id") int permissionId){
        permissionApi.deletePermission(permissionId);
        return AdminResponse.buildSuccess();
    }

    //获取某个角色的全部权限
    @RequestMapping("getrolepermission")
    public AdminResponse<Object> getRolePermission(@RequestParam(name = "roleId") int roleId){
        AdminResponse<ChangeRolePermissionResponse> response = new AdminResponse<>();
        response.setCode(200);
        response.setResult(roleApi.getRolePermissionByRoleId(roleId));
        return AdminResponse.buildSuccess(response);
    }

    //修改某个角色的全部权限
    @RequestMapping("confirmrolepermission")
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

    //按条件获取所有的用户及其权限信息


}
