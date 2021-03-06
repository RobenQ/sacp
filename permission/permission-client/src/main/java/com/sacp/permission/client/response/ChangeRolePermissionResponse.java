package com.sacp.permission.client.response;

import java.io.Serializable;
import java.util.List;

public class ChangeRolePermissionResponse implements Serializable {
    private List<PermissionResponse> have;
    private List<PermissionResponse> no;

    public List<PermissionResponse> getHave() {
        return have;
    }

    public void setHave(List<PermissionResponse> have) {
        this.have = have;
    }

    public List<PermissionResponse> getNo() {
        return no;
    }

    public void setNo(List<PermissionResponse> no) {
        this.no = no;
    }
}
