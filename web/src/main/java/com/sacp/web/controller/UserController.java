package com.sacp.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.StringUtils;
import com.sacp.member.client.api.IPTransferApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.member.client.util.SecurityUtil;
import com.sacp.permission.client.api.RoleApi;
import com.sacp.web.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Resource
    private HttpServletRequest httpServletRequest;

    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @DubboReference(version = "1.0")
    private IPTransferApi ipTransferApi;

    @DubboReference(version = "1.0")
    private RoleApi roleApi;

    @PostMapping("login")
    public UserResponse login(HttpServletRequest request, @RequestBody JSONObject user) throws Exception {
        UserResponse userResponse = new UserResponse();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = null;
        try{
            if (subject.isRemembered() || subject.isAuthenticated()){
                String nickName = (String) subject.getPrincipal();
                LoginResponse loginResponse = memberApi.getAuthInfo(nickName);
                if (loginResponse==null){
                    return UserResponse.buildFaild();
                }
                token = new UsernamePasswordToken(loginResponse.getNickName(),
                        loginResponse.getPassword(),true);

            }else {
                if (user.getString("password")==null || user.getString("nickName")==null){
                    return UserResponse.buildFaild();
                }
                token = new UsernamePasswordToken(user.getString("nickName"),
                        SecurityUtil.securityPassword(user.getString("password")),true);
            }
            subject.login(token);
            log.info("账号：{} 登录成功",subject.getPrincipal());
            userResponse.setCode(200);
            userResponse.setToken(subject.getSession().getId().toString());
            String sacpId = memberApi.getAuthInfo((String) subject.getPrincipal()).getSacpId();
            userResponse.setSacpId(sacpId);
            userResponse.setMessage("登录成功！");
            userResponse.setStatus("OK");
            LoginResponse loginResponse = memberApi.getAuthInfo(user.getString("nickName"));
            MemberRequest memberRequest = new MemberRequest();
            memberRequest.setSacpId(loginResponse.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(memberRequest).get(0);
            memberResponse.setPassword(null);
            userResponse.setResult(memberResponse);
            log.info("登录成功：{}",subject.getPrincipal());
            return userResponse;
        }catch (Exception e){
            String exceptionClassName = e.getClass().getName();
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                log.info("账号:{} 不存在",subject.getPrincipal());
                userResponse.setCode(201);
                userResponse.setMessage("账号不存在！");
                userResponse.setStatus("faild");
                userResponse.setResult("账号不存在");
                log.info("账号不存在");
                return userResponse;
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                log.info("密码错误");
                userResponse.setCode(201);
                userResponse.setMessage("密码错误！");
                userResponse.setStatus("faild");
                userResponse.setResult("密码错误");
                log.info("密码错误");
                return userResponse;
            }else {
                userResponse.setCode(201);
                log.info(subject.getSession().getId().toString());
                userResponse.setToken(subject.getSession().getId().toString());
                userResponse.setMessage("系统异常！");
                userResponse.setStatus("faild");
                userResponse.setResult("系统异常");
                log.info("系统异常");
                return userResponse;
            }
        }
    }

    @GetMapping("/logout")
    public UserResponse logout(){
        UserResponse adminResponse = new UserResponse();
        adminResponse.setCode(200);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
        adminResponse.setMessage("你已退出登录！");
        adminResponse.setStatus("OK");
        return adminResponse;
    }

    @PostMapping("signup")
    public UserResponse signUp(@RequestBody JSONObject user) throws NoSuchAlgorithmException {
        String nickName = user.getString("nickName");
        String avatar = user.getString("avatar");
        String gender = user.getString("gender");
        String password = user.getString("password");
        if (StringUtils.isNullOrEmpty(nickName)||StringUtils.isNullOrEmpty(avatar)
                ||StringUtils.isNullOrEmpty(gender)||StringUtils.isNullOrEmpty(password))
            return UserResponse.buildFaild("数据完整性校验错误！");
        LoginResponse authInfo = memberApi.getAuthInfo(nickName);
        //昵称呗被占用自接返回错误信息
        if (authInfo!=null)
            return UserResponse.buildFaild("该昵称已被占用!");
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setNickName(nickName);
        memberRequest.setAvatar(avatar);
        memberRequest.setGender(gender);
        memberRequest.setPassword(password);
        boolean member = memberApi.createMember(memberRequest);
        if (member)
            return UserResponse.buildSuccess("注册成功！");
        else
            return UserResponse.buildFaild("注册失败！");
    }

    @GetMapping("getUserInfo")
    public UserResponse getUserInfo(@RequestParam String sacpId){
        MemberRequest request = new MemberRequest();
        request.setSacpId(sacpId);
        List<MemberResponse> account = memberApi.getAccount(request);
        if (account.size()==0)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildSuccess(account.get(0));
    }

}
