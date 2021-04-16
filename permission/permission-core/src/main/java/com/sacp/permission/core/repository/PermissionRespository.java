package com.sacp.permission.core.repository;

import com.alibaba.fastjson.JSON;
import com.sacp.permission.client.response.PermissionResponse;
import com.sacp.permission.core.entity.Permission_info;
import com.sacp.permission.core.entity.Permission_infoExample;
import com.sacp.permission.core.entity.RolePermission;
import com.sacp.permission.core.entity.RolePermissionExample;
import com.sacp.permission.core.mapper.Permission_infoMapper;
import com.sacp.permission.core.mapper.RolePermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class PermissionRespository {

    @Autowired
    private Permission_infoMapper permissionInfoMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public long countPermission(){
        Permission_infoExample example = new Permission_infoExample();
        example.createCriteria().andIdIsNotNull();
        return permissionInfoMapper.countByExample(example);
    }

    //获取所有权限信息
    public List<Permission_info> getAllPermission(){
        Permission_infoExample example = new Permission_infoExample();
        example.createCriteria().andIdIsNotNull();
        return permissionInfoMapper.selectByExample(example);
    }

    public Permission_info getPermissonById(Integer id){
        Permission_info permission_info = permissionInfoMapper.selectByPrimaryKey(id);
        return permission_info;
    }

    //批量获取权限
    public List<PermissionResponse> getPermissionByIdList(Set<Integer> idList){
        List<PermissionResponse> permissionResponses = new ArrayList<>(idList.size());
        for (int id:idList) {
            PermissionResponse response = new PermissionResponse();
            BeanUtils.copyProperties(getPermissonById(id),response);
            permissionResponses.add(response);
        }
        return permissionResponses;
    }

    //增加一个权限
    @Transactional(rollbackFor = Exception.class)
    public Permission_info insertPermission(Permission_info info){
        info.setCreateTime(new Date());
        permissionInfoMapper.insertSelective(info);
        return permissionInfoMapper.selectByPrimaryKey(info.getId());
    }

    //更新一个权限信息
    @Transactional(rollbackFor = Exception.class)
    public Permission_info updatePermission(Permission_info info){
        info.setModifyTime(new Date());
        permissionInfoMapper.updateByPrimaryKeySelective(info);
        Permission_info permissionInfo = permissionInfoMapper.selectByPrimaryKey(info.getId());
        log.info(JSON.toJSONString(permissionInfo));
        return permissionInfo;
    }

    //删除一个权限
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePermission(Integer id){
        int i = permissionInfoMapper.deleteByPrimaryKey(id);
        return i==1?true:false;
    }

    //根据sacpId获取权限
    public List<Permission_info> getPermissionsByRoleId(Integer roleId){
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        List<Permission_info> permission_infos = new ArrayList<>(rolePermissions.size());
        for (RolePermission rolePermisison:rolePermissions) {
            Permission_info info = permissionInfoMapper.selectByPrimaryKey(rolePermisison.getPermissionId());
            permission_infos.add(info);
        }
        return permission_infos;
    }

}
