package com.sacp.web.aspect;

import com.alibaba.fastjson.JSONObject;
import com.sacp.course.client.api.CourseApi;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.web.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CheckAspect {

    @DubboReference(version = "1.0")
    private MemberApi memberApi;
    @DubboReference(version = "1.0")
    private CourseApi courseApi;
    @DubboReference(version = "1.0")
    private ForumApi forumApi;

    @Pointcut("@annotation(com.sacp.web.annotation.CheckMemberCourse)")
    public void checkMemberCoursePointcut(){

    }

    @Pointcut("@annotation(com.sacp.web.annotation.CheckAllowPost)")
    public void checkAllowPostPointcut(){

    }

    @Around("checkMemberCoursePointcut()")
    public Object checkMemberCourse(ProceedingJoinPoint point) throws Throwable {
        UserResponse response = new UserResponse();
        String nickName  = (String) SecurityUtils.getSubject().getPrincipal();
        if (nickName==null || "".equals(nickName)){
            response.setCode(301);
            response.setMessage("请先登录！");
            response.setResult("请先登录！");
            return response;
        }
        LoginResponse authInfo = memberApi.getAuthInfo(nickName);
        if (authInfo==null || authInfo.getStatusCode()==102){
            response.setCode(500);
            response.setMessage("当前登录的账号异常！");
            response.setResult("当前登录的账号异常！");
            return response;
        }
        Object[] args = point.getArgs();
        JSONObject jsonObject = (JSONObject) args[0];
        int courseId = jsonObject.getIntValue("courseId");
        boolean b = courseApi.isJoinCourse(courseId, authInfo.getSacpId());
        if (b){
            return point.proceed();
        }else {
            response.setCode(401);
            response.setMessage("加入课程后才能观看或下载！");
            response.setResult("加入课程后才能观看或下载！");
            return response;
        }
    }

    @Around("checkAllowPostPointcut()")
    public Object checkAllowPost(ProceedingJoinPoint point) throws Throwable {
        UserResponse response = new UserResponse();
        String nickName  = (String) SecurityUtils.getSubject().getPrincipal();
        if (nickName==null || "".equals(nickName)){
            response.setCode(301);
            response.setMessage("请先登录！");
            response.setResult("请先登录！");
            return response;
        }
        LoginResponse authInfo = memberApi.getAuthInfo(nickName);
        if (authInfo==null || authInfo.getStatusCode()==102){
            response.setCode(500);
            response.setMessage("当前登录的账号异常！");
            response.setResult("当前登录的账号异常！");
            return response;
        }
        if (authInfo.getStatusCode()==101){
            response.setCode(402);
            response.setMessage("你已被禁言,操作失败！");
            response.setResult("你已被禁言,操作失败！");
            return response;
        }
        return point.proceed();
    }
}
