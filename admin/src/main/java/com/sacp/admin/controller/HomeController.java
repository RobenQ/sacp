package com.sacp.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.admin.util.HomeService;
import com.sacp.course.client.api.CourseApi;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.permission.client.api.PermissionApi;
import com.sacp.permission.client.api.RoleApi;
import org.apache.commons.collections.map.HashedMap;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;
    @DubboReference(version = "1.0")
    private MemberApi memberApi;
    @DubboReference(version = "1.0")
    private CourseApi courseApi;
    @DubboReference(version = "1.0")
    private RoleApi roleApi;
    @DubboReference(version = "1.0")
    private PermissionApi permissionApi;
    @DubboReference(version = "1.0")
    private ForumApi forumApi;

    @GetMapping("getAllMember")
    public AdminResponse getAllMember(){
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(200);
        Subject subject = SecurityUtils.getSubject();
        adminResponse.setToken(subject.getSession().getId().toString());
        adminResponse.setMessage("登录成功！");
        adminResponse.setStatus("success");
        Map<String,Long> result = new HashedMap(8);
        result.put("memebers",memberApi.countMember());
        result.put("courses",courseApi.countCourse());
        result.put("roles",roleApi.countRole());
        result.put("permissions",permissionApi.countPermission());
        result.put("posts",forumApi.countPost());
        adminResponse.setResult(result);
        return adminResponse;
    }
}
