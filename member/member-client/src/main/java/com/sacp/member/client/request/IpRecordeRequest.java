package com.sacp.member.client.request;

import java.io.Serializable;

public class IpRecordeRequest implements Serializable {
    private String ipStr;
    private String sacpId;

    public IpRecordeRequest() {
    }

    public IpRecordeRequest(String ipStr, String sacpId) {
        this.ipStr = ipStr;
        this.sacpId = sacpId;
    }

    public String getIpStr() {
        return ipStr;
    }

    public void setIpStr(String ipStr) {
        this.ipStr = ipStr;
    }

    public String getSacpId() {
        return sacpId;
    }

    public void setSacpId(String sacpId) {
        this.sacpId = sacpId;
    }
}
