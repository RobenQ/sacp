package com.sacp;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.permission.client.request.RolesRequest;
import com.sacp.permission.client.response.RolesResponse;
import com.sacp.permission.core.PermissionApp;
import com.sacp.permission.core.repository.RolePermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PermissionApp.class)
public class AppTest 
{
    @DubboReference(version = "1.0",check = false)
    private RoleApi roleApi;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Test
    public void testInsertRole(){
        RolesRequest request = new RolesRequest();
        request.setRoleName("test");
        request.setExpression("test");
        RolesResponse response = roleApi.addRole(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        int weekYear = Calendar.getInstance().getWeekYear();
        String header =  String.valueOf(weekYear).substring(2);
        Random random = new Random();
        int a = random.nextInt(999);
        String mid = ""+String.format("%03d",a);
        System.out.println(header+mid+System.currentTimeMillis());
    }

    @Test
    public void RoleHaveAndNoPermission(){
        System.out.println(JSON.toJSONString(roleApi.getRolePermissionByRoleId(1)));
        System.out.println("finish");
    }
}
