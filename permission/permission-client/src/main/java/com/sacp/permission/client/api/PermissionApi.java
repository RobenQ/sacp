package com.sacp.permission.client.api;

import com.sacp.permission.client.request.PermissionRequest;
import com.sacp.permission.client.response.PermissionResponse;

import java.util.List;

public interface PermissionApi {

    public List<PermissionResponse> getAllPermission();
    public PermissionResponse addPermission(PermissionRequest request);
    public PermissionResponse updatePermission(PermissionRequest request);
    public boolean deletePermission(Integer id);
}
