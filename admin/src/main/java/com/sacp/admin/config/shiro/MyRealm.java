package com.sacp.admin.config.shiro;

import com.sacp.member.client.response.LoginResponse;
import com.sacp.admin.service.MemberService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private MemberService memberService;

    @Override
    public String getName() {
        return "sacp-admin";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("进入认证，nickName:{}",token.getPrincipal());
        String nickName = (String) token.getPrincipal();
        if (nickName==null)
            return null;
        LoginResponse loginResponse = memberService.getAuthInfo(nickName);
        log.info("admin->member success");
        if (loginResponse==null){
            log.info("没有该账号信息");
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginResponse.getNickName(),
                loginResponse.getPassword(),getName());
        return info;
    }
}
