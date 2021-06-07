package com.sacp.member.core.service;

import com.alibaba.fastjson.JSON;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sacp.member.client.api.IPTransferApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.IpRecordeRequest;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.AddressResponse;
import com.sacp.member.core.entity.LoginRecord;
import com.sacp.member.core.repository.IpReponsitory;
import com.sacp.member.core.repository.MemberRepository;
import com.sacp.member.core.util.CheckIp;
import org.apache.dubbo.config.annotation.DubboService;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@DubboService(version = "1.0")
public class IPService implements IPTransferApi {

    private static final String LOCAL_NETWORK = "局域网";
    private DbSearcher searcher = null;
    private Logger logger = LoggerFactory.getLogger(IPService.class);

    @Autowired
    private IpReponsitory ipReponsitory;
    @Autowired
    private MemberApi memberApi;

    //@PostConstruct
    //public void init(){
    //    try {
    //        File resource  = ResourceUtils.getFile("E:\\bishe\\ip2region.db");
    //        databaseReader  =new DatabaseReader.Builder(resource).build();
    //    } catch (IOException e) {
    //        logger.error("read mmdb file faild !");
    //        e.printStackTrace();
    //    }
    //}

    @PostConstruct
    public void init() throws DbMakerConfigException, FileNotFoundException {
        String dbPath = ResourceUtils.getFile("E:\\bishe\\ip2region.db").getPath();
        File file = new File(dbPath);
        if (!file.exists()) {
            logger.error("地址库文件不存在");
        }
        searcher = new DbSearcher(new DbConfig(), dbPath);
    }

    //@Override
    //public AddressResponse Ip2Address(String ipStr) {
    //    AddressResponse response = new AddressResponse();
    //    if (ipStr.startsWith("192.168")||("127.0.0.1").equals(ipStr)){
    //        response.setIp(ipStr);
    //        response.setCountry(LOCAL_NETWORK);
    //        response.setCity(LOCAL_NETWORK);
    //        response.setTown(LOCAL_NETWORK);
    //        return response;
    //    }
    //    InetAddress inetAddress = null;
    //    try {
    //        inetAddress = InetAddress.getByName(ipStr);
    //    } catch (UnknownHostException e) {
    //        logger.error("the {} is not a ip address",ipStr);
    //        e.printStackTrace();
    //    }
    //    response.setIp(ipStr);
    //    try {
    //        response.setCountry(databaseReader.country(inetAddress).getCountry().getNames().get("zh-CN"));
    //        response.setCountry(databaseReader.city(inetAddress).getMostSpecificSubdivision().getNames().get("zh-CN"));
    //        response.setCountry(databaseReader.city(inetAddress).getCity().getNames().get("zh-CN"));
    //    } catch (IOException e) {
    //        logger.error("read mmdb file faild !");
    //        e.printStackTrace();
    //    } catch (GeoIp2Exception e) {
    //        logger.error("transfer ip faild !");
    //        e.printStackTrace();
    //    }
    //    return response;
    //}


    @Override
    public AddressResponse Ip2Address(String ipStr) throws IOException, DbMakerConfigException {
        AddressResponse response = new AddressResponse();
        if (ipStr.startsWith("192.168")||("127.0.0.1").equals(ipStr)){
            response.setIp(ipStr);
            response.setCountry(LOCAL_NETWORK);
            response.setCity(LOCAL_NETWORK);
            response.setTown(LOCAL_NETWORK);
            return response;
        }
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(ipStr);
        } catch (UnknownHostException e) {
            logger.error("the {} is not a ip address",ipStr);
            e.printStackTrace();
        }
        response.setIp(ipStr);
        String address = CheckIp.getAddress(searcher, ipStr);
        String[] addressList = address.split("\\|");
        response.setCountry(addressList[0]);
        response.setCity(addressList[1]+"|"+addressList[2]);
        response.setTown(addressList[3]);
        return response;
    }

    @Override
    public void recoreLogin(IpRecordeRequest request) throws IOException, DbMakerConfigException {
        logger.info("call %m ,IpRecordeRequest:{}", JSON.toJSONString(request));
        AddressResponse addressResponse = Ip2Address(request.getIpStr());
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setIp(addressResponse.getIp());
        loginRecord.setSacpId(request.getSacpId());
        loginRecord.setCountry(addressResponse.getCountry());
        loginRecord.setCity(addressResponse.getCity());
        loginRecord.setTown(addressResponse.getTown());
        loginRecord.setLoginTime(new Date());
        MemberRequest request1 = new MemberRequest();
        request1.setSacpId(request.getSacpId());
        loginRecord.setNickName(memberApi.getAccount(request1).get(0).getNickName());
        ipReponsitory.insertLoginRecorde(loginRecord);
        logger.info("call %m success,info:{}", JSON.toJSONString(loginRecord));
    }
}
