package com.sacp.member.core.service;

import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.member.client.util.SecurityUtil;
import com.sacp.member.core.entity.Account;
import com.sacp.member.core.entity.AccountExample;
import com.sacp.member.core.repository.MemberRepository;
import com.sacp.member.core.util.SacpIdUtil;
import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.permission.client.request.MemberRoleRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(version = "1.0")
public class MemberService implements MemberApi {

    @Autowired
    private MemberRepository memberRepository;

    @DubboReference(version = "1.0",check = false)
    private RoleApi roleApi;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createMember(MemberRequest request) throws NoSuchAlgorithmException {
        Account account = new Account();
        BeanUtils.copyProperties(request,account);
        account.setCreateTime(new Date());
        account.setSacpId(SacpIdUtil.generateSacpId());
        account.setPassword(SecurityUtil.securityPassword(request.getPassword()));
        account.setClassfifyId(2);
        account.setStatus(100);
        //分配角色
        MemberRoleRequest memberRoleRequest = new MemberRoleRequest();
        memberRoleRequest.setRoleId(2);
        memberRoleRequest.setCreateTime(new Date());
        memberRoleRequest.setSacpId(account.getSacpId());
        roleApi.addMemberRole(memberRoleRequest);
        return memberRepository.insertMemebrt(account);
    }

    @Override
    public LoginResponse getAuthInfo(String nickName) {
        Account account = memberRepository.getInfoByNickName(nickName);
        if (account==null)
            return null;
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSacpId(account.getSacpId());
        loginResponse.setNickName(account.getNickName());
        loginResponse.setPassword(account.getPassword());
        loginResponse.setStatusCode(account.getStatus());
        return loginResponse;
    }
}
