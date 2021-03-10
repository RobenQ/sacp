package com.sacp.member.core.service;

import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.member.core.entity.Account;
import com.sacp.member.core.entity.AccountExample;
import com.sacp.member.core.repository.MemberRepository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0")
public class MemberService implements MemberApi {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<MemberResponse> getAccount(MemberRequest request) {
        Account account = new Account();
        BeanUtils.copyProperties(request,account);
        List<Account> account1 = memberRepository.getAccount(account);
        List<MemberResponse> response = new ArrayList<>(account1.size());
        for (Account a:account1) {
            MemberResponse memberResponse = new MemberResponse();
            BeanUtils.copyProperties(a,memberResponse);
            response.add(memberResponse);
        }
        return response;
    }

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
