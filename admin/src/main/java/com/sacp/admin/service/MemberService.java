package com.sacp.admin.service;

import com.sacp.member.client.api.IPTransferApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    @DubboReference(version = "1.0")
    private IPTransferApi ipTransferApi;

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    public LoginResponse getAuthInfo(String nickName){
        return memberApi.getAuthInfo(nickName);
    }



}
