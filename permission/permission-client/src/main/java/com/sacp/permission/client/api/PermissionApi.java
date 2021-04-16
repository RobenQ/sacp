package com.sacp.permission.client.api;

import com.sacp.permission.client.request.PermissionRequest;
import com.sacp.permission.client.response.PermissionResponse;

import java.util.List;

public interface PermissionApi {

    /**
     * 获取所有的权限新
     * @return 所有权限的信息列表
     */
    public List<PermissionResponse> getAllPermission();

    /**
     * 根据用户账户的sacpId获取用户的权限信息
     * @param sacpId 用户账户的sacpId
     * @return 权限信息的字符串形式的信息列表
     */
    public List<String> getPermissionBySacpId(String sacpId);

    /**
     * 添加权限，用于向系统中添加权限
     * @param request 权限请求对象
     * @return 添加的权限
     */
    public PermissionResponse addPermission(PermissionRequest request);

    /**
     * 更新权限信息
     * @param request 权限请求对象
     * @return 更新后的权限
     */
    public PermissionResponse updatePermission(PermissionRequest request);

    /**
     * 根据权限ID删除权限
     * @param id 权限ID
     * @return 是否删除成功
     */
    public boolean deletePermission(Integer id);

    public long countPermission();
}
