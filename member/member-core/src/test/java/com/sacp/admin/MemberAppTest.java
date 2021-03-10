package com.sacp.admin;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.sacp.member.client.api.IPTransferApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.core.MemberApp;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = MemberApp.class)
@RunWith(SpringRunner.class)
public class MemberAppTest
{
    /**
     * Rigorous Test :-)
     */
    @DubboReference(version = "1.0")
    private IPTransferApi ipTransferAPI;

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @Test
    public void test()
    {
        MemberRequest request = new MemberRequest();
//        request.setNickName("z");
        System.out.println(JSON.toJSONString(memberApi.getAccount(request)));
    }
}
