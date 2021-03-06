package com.sacp.member.core.service;

import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.member.core.entity.Account;
import com.sacp.member.core.repository.MemberRepository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0")
public class MemberService implements MemberApi {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public LoginResponse getAuthInfo(String nickName) {
        Account account = memberRepository.getInfoByNickName(nickName);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSacpId(account.getSacpId());
        loginResponse.setNickName(account.getNickName());
        loginResponse.setPassword(account.getPassword());
        loginResponse.setStatusCode(account.getStatus());
        return loginResponse;
    }
}
