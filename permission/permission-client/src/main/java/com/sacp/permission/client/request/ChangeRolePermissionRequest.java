package com.sacp.permission.client.request;

import java.io.Serializable;
import java.util.List;

public class ChangeRolePermissionRequest implements Serializable {
    private Integer roleId;
    private List<Integer> permissionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(List<Integer> permissionId) {
        this.permissionId = permissionId;
    }
}
