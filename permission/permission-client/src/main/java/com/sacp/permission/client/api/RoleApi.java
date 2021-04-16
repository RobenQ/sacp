package com.sacp.permission.client.api;

import com.sacp.permission.client.request.MemberRoleRequest;
import com.sacp.permission.client.request.RolesRequest;
import com.sacp.permission.client.request.ChangeRolePermissionRequest;
import com.sacp.permission.client.response.ChangeRolePermissionResponse;
import com.sacp.permission.client.response.MemberRoleResponse;
import com.sacp.permission.client.response.RolesResponse;

import java.util.List;

/**
 * 用户角色管理相关dubbo接口
 * sacp平台中每个用户有且只有一个角色
 * @author zhouqing
 * @date 2021/04/02
 */
public interface RoleApi {

    /**
     * 根据sacpId查询用户角色
     * @param sacpId 用户账户的sacpId
     * @return 用户全部角色的字符串列表，为了日后扩展功能才返回一个List
     */
    public List<String> getRolesBySacpId(String sacpId);

    /**
     * 根据用户sacpId获取用户角色
     * @param sacpId 用户账户的sacpId
     * @return 用户角色
     */
    public RolesResponse getRoleBySacpId(String sacpId);

    /**
     * 获取系统中的全部角色信息
     * @return 全部角色
     */
    public List<RolesResponse> getAllRole();

    /**
     * 根据角色ID获取角色信息
     * @param id 角色ID
     * @return 对应ID的角色
     */
    public RolesResponse getRoleById(Integer id);

    /**
     * 添加角色
     * @param request 角色请求对象
     * @return 添加的角色
     */
    public RolesResponse addRole(RolesRequest request);

    /**
     * 更新角色信息
     * @param request 角色请求对象
     * @return 更新后的角色
     */
    public RolesResponse updateRole(RolesRequest request);

    /**
     * 根据角色ID删除一个角色
     * @param id 角色ID
     * @return 是否删除成功
     */
    public boolean deleteRole(Integer id);

    /**
     * 根据角色ID获取角色的拥有的和不拥有的权限信息
     * @param roleId 角色ID
     * @return 角色权限状况对象，包含角色拥有的和不拥有的权限
     */
    public ChangeRolePermissionResponse getRolePermissionByRoleId(Integer roleId);

    /**
     * 更新角色的权限信息
     * @param request 用户权限状况对象
     * @return 是否更新成功
     */
    public boolean updateRolePermission(ChangeRolePermissionRequest request);

    /**
     * 根据角色ID获取拥有该角色和用户关的联信息
     * @param id 角色ID
     * @return 角色和用户的关联信息列表
     */
    public List<MemberRoleResponse> getMemberRoleByRoleId(Integer id);

    /**
     * 获取全部的角色和用户的关联信息
     * @return 全部角色和用户的关联信息列表
     */
    public List<MemberRoleResponse> getAllMemberRole();

    /**
     * 修改用户的角色
     * @param request 角色和用户关联信息请求对象
     * @return 是否更新成功
     */
    public boolean updateMemberRole(MemberRoleRequest request);

    /**
     * 添加角色和用户的关联信息，一般用户在注册的时候会调用该方法
     * @param request 角色和用户关联信息请求对象
     * @return 是否添加成功
     */
    public boolean addMemberRole(MemberRoleRequest request);

    public long countRole();

}
