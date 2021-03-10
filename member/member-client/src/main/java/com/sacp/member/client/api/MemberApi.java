package com.sacp.member.client.api;

import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.member.client.response.MemberResponse;

import java.util.List;

public interface MemberApi {
    public LoginResponse getAuthInfo(String nickName);
    public List<MemberResponse> getAccount(MemberRequest request);
}
