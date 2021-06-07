package com.sacp.member.client.api;

import com.sacp.member.client.request.IpRecordeRequest;
import com.sacp.member.client.response.AddressResponse;
import org.lionsoul.ip2region.DbMakerConfigException;

import java.io.IOException;

/**
 * IP服务，用户处理IP相关数据
 */
public interface IPTransferApi {
    /**
     * 获取IP地址所在地理位置
     * @param ipStr IP地址字符串
     * @return IP地址地理位置类
     */
    public AddressResponse Ip2Address(String ipStr) throws IOException, DbMakerConfigException;

    /**
     * 记录用户每次登录的IP信息
     * @param request IP地址字符串
     */
    public void recoreLogin(IpRecordeRequest request) throws IOException, DbMakerConfigException;
}
