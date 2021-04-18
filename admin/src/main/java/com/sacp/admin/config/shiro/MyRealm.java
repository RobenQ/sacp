package com.sacp.admin.config.shiro;

import com.alibaba.fastjson.JSON;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.api.RoleApi;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @DubboReference(version = "1.0")
    private RoleApi roleApi;

    @DubboReference(version = "1.0")
    private PermissionApi permissionApi;

    @Override
    public String getName() {
        return "sacp-admin";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String nickName = (String) principals.getPrimaryPrincipal();
        String sacpId = memberApi.getAuthInfo(nickName).getSacpId();
        List<String> roleStringList = roleApi.getRolesBySacpId(sacpId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleStringList);
        info.addStringPermissions(permissionApi.getPermissionBySacpId(sacpId));
        return info;
    }

    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String nickName = (String) token.getPrincipal();
        if (nickName==null)
            return null;
        LoginResponse loginResponse = memberApi.getAuthInfo(nickName);
        log.info("admin->member:success");
        if (loginResponse==null){
            log.info("没有该账号信息");
            return null;
        }
        if (loginResponse.getStatusCode()==102){
            log.info("该账号被冻结");
            throw new LockedAccountException("账号被冻结");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginResponse.getNickName(),
                loginResponse.getPassword(),getName());
        return info;
    }
}
