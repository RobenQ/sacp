package com.sacp.admin.config.shiro;

import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
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

    @Override
    public String getName() {
        return "sacp-admin";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String sacId = (String) principals.getPrimaryPrincipal();
        List<String> roleStringList = roleApi.getRolesBySacpId(sacId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleStringList);
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
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginResponse.getNickName(),
                loginResponse.getPassword(),getName());
        return info;
    }
}
