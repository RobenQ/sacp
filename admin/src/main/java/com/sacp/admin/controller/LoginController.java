package com.sacp.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sacp.member.client.api.IPTransferApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.member.client.util.SecurityUtil;
import com.sacp.admin.response.AdminResponse;
import com.sacp.permission.client.api.RoleApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Resource
    private HttpServletRequest httpServletRequest;

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @DubboReference(version = "1.0")
    private IPTransferApi ipTransferApi;

    @DubboReference(version = "1.0")
    private RoleApi roleApi;

    @GetMapping("/tologin")
    public AdminResponse<String> toLogin(HttpServletRequest request){
        log.info("get login");
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(301);
        Subject subject = SecurityUtils.getSubject();
        adminResponse.setToken(subject.getSession().getId().toString());
        adminResponse.setMessage("未登录或登录身份失效，请登录！");
        adminResponse.setStatus("faild");
        adminResponse.setResult("请登录");
        return adminResponse;
    }

    @RequestMapping("/unauthoriz")
    public AdminResponse<String> unauthoriz(){
        log.info("unauthoriz");
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(100);
        Subject subject = SecurityUtils.getSubject();
        adminResponse.setMessage("你还没有权限访问！");
        adminResponse.setStatus("faild");
        adminResponse.setResult("faild");
        log.info("权限认证失败");
        return adminResponse;
    }

    @GetMapping("/success")
    public AdminResponse<String> success(HttpServletRequest request){
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(202);
        Subject subject = SecurityUtils.getSubject();
        adminResponse.setToken(subject.getSession().getId().toString());
        adminResponse.setMessage("登录成功！");
        adminResponse.setStatus("success");
        adminResponse.setResult("登录成功");
        return adminResponse;
    }

    //防止shiro死循环重定向
    @GetMapping("login")
    public AdminResponse<String> relogin(){
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(203);
        adminResponse.setMessage("请重新登陆！");
        adminResponse.setStatus("faild");
        adminResponse.setResult("重新登陆");
        log.info("重新登陆");
        return adminResponse;
    }

    //真正的登录
    @PostMapping("/login")
    public AdminResponse<String> login(HttpServletRequest request, @RequestBody JSONObject user) throws Exception {
        AdminResponse adminResponse = new AdminResponse();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getString("username"),
                SecurityUtil.securityPassword(user.getString("password")));
        token.setRememberMe(true);
        try{
            subject.login(token);
            log.info("账号：{} 登录成功",subject.getPrincipal());
            adminResponse.setCode(200);
            adminResponse.setToken(subject.getSession().getId().toString());
            String sacpId = memberApi.getAuthInfo((String) subject.getPrincipal()).getSacpId();
            adminResponse.setSacpId(sacpId);
            adminResponse.setMessage("登录成功！");
            adminResponse.setStatus("OK");
            adminResponse.setResult("登录成功");
            log.info("登录成功：{}",subject.getPrincipal());
            return adminResponse;
        }catch (Exception e){
            String exceptionClassName = e.getClass().getName();
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                log.info("账号:{} 不存在",subject.getPrincipal());
                adminResponse.setCode(201);
                adminResponse.setMessage("账号不存在！");
                adminResponse.setStatus("faild");
                adminResponse.setResult("账号不存在");
                log.info("账号不存在");
                return adminResponse;
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                log.info("密码错误");
                adminResponse.setCode(201);
                adminResponse.setMessage("密码错误！");
                adminResponse.setStatus("faild");
                adminResponse.setResult("密码错误");
                log.info("密码错误");
                return adminResponse;
            }else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                log.info("该账号被冻结");
                adminResponse.setCode(202);
                adminResponse.setMessage("该账号被冻结！");
                adminResponse.setStatus("faild");
                adminResponse.setResult("该账号被冻结");
                log.info("该账号被冻结");
                return adminResponse;
            } else {
                adminResponse.setCode(201);
                log.info(subject.getSession().getId().toString());
                adminResponse.setToken(subject.getSession().getId().toString());
                adminResponse.setMessage("系统异常！");
                adminResponse.setStatus("faild");
                adminResponse.setResult("系统异常");
                log.info("系统异常");
                return adminResponse;
            }
        }

    }

    //获取用户信息
    @RequiresRoles("admin")
    @GetMapping("/admininfo")
    public AdminResponse<Map<String,Object>> adminInfo(@RequestParam String sacpId){
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(200);
        Subject subject = SecurityUtils.getSubject();
        adminResponse.setToken(subject.getSession().getId().toString());
        adminResponse.setMessage("success");
        adminResponse.setStatus("OK");
        if (sacpId==null || sacpId.equals("")){
            String nickName = (String) subject.getPrincipal();
            sacpId = memberApi.getAuthInfo(nickName).getSacpId();
        }
        adminResponse.setRoles(roleApi.getRolesBySacpId(sacpId));

        MemberRequest request = new MemberRequest();
        request.setSacpId(sacpId);
        MemberResponse memberResponse = memberApi.getAccount(request).get(0);
        memberResponse.setPassword(null);
        adminResponse.setResult(memberResponse);
        return adminResponse;
    }

    @PostMapping("/logout")
    public AdminResponse<Map<String,Object>> logout(){
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(200);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
        adminResponse.setMessage("你已退出登录！");
        adminResponse.setStatus("OK");
        return adminResponse;
    }

}
