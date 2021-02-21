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
        return new LoginResponse(account.getNickName(),account.getPassword(),account.getStatus());
    }
}
