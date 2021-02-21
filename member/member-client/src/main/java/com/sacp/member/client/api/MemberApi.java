package com.sacp.member.client.api;

import com.sacp.member.client.response.LoginResponse;

public interface MemberApi {
    public LoginResponse getAuthInfo(String nickName);
}
