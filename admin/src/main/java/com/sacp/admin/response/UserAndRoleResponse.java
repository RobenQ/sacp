package com.sacp.admin.response;

import com.sacp.member.client.response.MemberResponse;
import com.sacp.permission.client.response.RolesResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAndRoleResponse {
    private MemberResponse member;
    private RolesResponse role;
}
