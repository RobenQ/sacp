package com.sacp.permission.client.response;

import java.io.Serializable;
import java.util.Date;

public class RolesResponse implements Serializable {
    private Integer id;
    private String roleName;
    private String expression;
    private Date createTime;
    private Date modifyTime;

    public Date getModifyTime() {
        return modifyTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
