package com.sacp.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.admin.response.UserAndRoleResponse;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.member.client.util.SecurityUtil;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.permission.client.response.RolesResponse;
import com.sacp.web.client.api.SacpWebApi;
import com.sacp.web.client.response.ActiveUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @DubboReference(version = "1.0")
    private MemberApi memberApi;
    @DubboReference(version = "1.0")
    private RoleApi roleApi;
    @DubboReference(version = "1.0",check = false)
    private SacpWebApi sacpWebApi;

    @PostMapping("getMemeberRoleInfo")
    public AdminResponse getMemeberRoleInfo(@RequestBody MemberRequest memberRequest){
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
    }

    @PostMapping("disallowPost")
    public AdminResponse disallowPost(@RequestBody JSONObject jsonObject){
        String sacpId = jsonObject.getString("sacpId");
        boolean b = memberApi.modifyStatus(sacpId, 101);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("disallowLogin")
    public AdminResponse disallowLogin(@RequestBody JSONObject jsonObject){
        String sacpId = jsonObject.getString("sacpId");
        boolean b = memberApi.modifyStatus(sacpId, 102);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("resetStatus")
    public AdminResponse resetStatus(@RequestBody JSONObject jsonObject){
        String sacpId = jsonObject.getString("sacpId");
        boolean b = memberApi.modifyStatus(sacpId, 100);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("resetPwd")
    public AdminResponse resetPwd(@RequestBody JSONObject jsonObject) throws NoSuchAlgorithmException {
        String sacpId = jsonObject.getString("sacpId");
        boolean b = memberApi.modifyPassword(sacpId, SecurityUtil.securityPassword("sacp111111"));
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("activrUsers")
    public AdminResponse getAllActiveUsers(){
        List<ActiveUserResponse> allActive = sacpWebApi.getAllActive();
        return AdminResponse.buildSuccess("获取在线用户信息成功！",allActive);
    }

    @PostMapping("offLineUser")
    public AdminResponse offLineUser(@RequestBody JSONObject request){
        String sessionId = request.getString("sessionId");
        boolean b = sacpWebApi.offLineUser(sessionId);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

}
