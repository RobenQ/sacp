package com.sacp.web.config;

import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.api.RoleApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        return "sacp-web";
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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String nickName = (String) token.getPrincipal();
        if (nickName==null)
            return null;
        LoginResponse loginResponse = memberApi.getAuthInfo(nickName);
        if (loginResponse==null){
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
