package com.sacp.permission.client.response;

import java.io.Serializable;
import java.util.List;

public class RolePermissionResponse implements Serializable {
    private RolesResponse role;
    private List<PermissionResponse> permissions;

    public RolesResponse getRole() {
        return role;
    }

    public void setRole(RolesResponse role) {
        this.role = role;
    }

    public List<PermissionResponse> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionResponse> permissions) {
        this.permissions = permissions;
    }
}
