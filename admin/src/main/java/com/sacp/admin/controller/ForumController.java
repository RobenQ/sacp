package com.sacp.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.course.client.request.CourseRequest;
import com.sacp.course.client.response.CourseResponse;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.request.PostRequest;
import com.sacp.forum.client.request.ReplyRequest;
import com.sacp.forum.client.response.PostResponse;
import com.sacp.forum.client.response.ReplyResponse;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class ForumController {
    @DubboReference(version = "1.0")
    private MemberApi memberApi;
    @DubboReference(version = "1.0")
    private ForumApi forumApi;

    @PostMapping("searchPost")
    public AdminResponse searchPost(@RequestBody JSONObject request){
        int postId = request.getIntValue("postId");
        if (postId!=0){
            PostResponse postById = forumApi.getPostById2(postId);
            ArrayList<PostResponse> postResponses = new ArrayList<PostResponse>();
            postResponses.add(postById);
            return AdminResponse.buildSuccess((postResponses));
        }
        String nickName = request.getString("author");
        JSONArray createTime1 = request.getJSONArray("createTime");
        List<Date> createTime = null;
        if (createTime1!=null){
            createTime = createTime1.toJavaList(Date.class);
        }
        int forumId = request.getIntValue("forumId");
        String sacpId = null;
        if (nickName!=null && !("".equals(nickName))){
            LoginResponse authInfo = memberApi.getAuthInfo(nickName);
            if (authInfo!=null)
                sacpId = authInfo.getSacpId();
        }
        PostRequest postRequest = new PostRequest();
        if (sacpId!=null)
            postRequest.setSacpId(sacpId);
        if (forumId!=0)
            postRequest.setBlockId(forumId);
        List<PostResponse> postResponses = forumApi.getPost(postRequest,createTime);

        return AdminResponse.buildSuccess(postResponses);
    }

    @PostMapping("searchPostReply")
    public AdminResponse searchPostReply(@RequestBody JSONObject request){
        int postId = request.getIntValue("postId");
        String nickName = request.getString("author");
        JSONArray createTime1 = request.getJSONArray("createTime");
        List<Date> createTime = null;
        if (createTime1!=null){
            createTime = createTime1.toJavaList(Date.class);
        }
        String sacpId = null;
        if (nickName!=null && !("".equals(nickName))){
            LoginResponse authInfo = memberApi.getAuthInfo(nickName);
            if (authInfo!=null)
                sacpId = authInfo.getSacpId();
        }
        ReplyRequest replyRequest = new ReplyRequest();
        if (sacpId!=null)
            replyRequest.setSacpId(sacpId);
        if (postId!=0)
            replyRequest.setPostId(postId);
        List<ReplyResponse> postResponses = forumApi.getPostReply(replyRequest,createTime);
        return AdminResponse.buildSuccess(postResponses);
    }

    @PostMapping("offLinePost")
    public AdminResponse offLinePost(@RequestBody JSONObject request){
        int postId = request.getIntValue("postId");
        boolean b = forumApi.deletePost(postId);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("offLinePostReply")
    public AdminResponse offLinePostReply(@RequestBody JSONObject request){
        int replyId = request.getIntValue("replyId");
        boolean b = forumApi.deleteReply(replyId);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("onLinePost")
    public AdminResponse onLinePost(@RequestBody JSONObject request){
        int postId = request.getIntValue("postId");
        boolean b = forumApi.recoveryPost(postId);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

    @PostMapping("onLinePostReply")
    public AdminResponse onLinePostReply(@RequestBody JSONObject request){
        int replyId = request.getIntValue("replyId");
        boolean b = forumApi.recoveryReply(replyId);
        if (b){
            return AdminResponse.buildSuccess();
        } else {
            return AdminResponse.buildFaild();
        }
    }

}
