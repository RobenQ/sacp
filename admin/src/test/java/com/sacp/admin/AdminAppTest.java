package com.sacp.admin;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.sacp.admin.controller.RoleController;
import com.sacp.member.client.util.SecurityUtil;
import com.sacp.permission.client.request.MemberRoleRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = AdminApp.class)
@RunWith(SpringRunner.class)
public class AdminAppTest
{

    @Autowired
    private RoleController roleController;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws NoSuchAlgorithmException {
        System.out.println(SecurityUtil.securityPassword("111111"));
    }

    @Test
    public void testRoleController(){
        MemberRoleRequest request = new MemberRoleRequest();
//        request.setSacpId("218681613907234457");
        System.out.println(JSON.toJSONString(roleController.getMemberAndRole(request)));
    }
}
