package com.sacp.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.sacp.course.client.api.CourseApi;
import com.sacp.course.client.response.CourseResponse;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.request.PostRequest;
import com.sacp.forum.client.request.ReplyRequest;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.client.response.PostResponse;
import com.sacp.forum.client.response.ReplyResponse;
import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.web.response.PostInfoResponse;
import com.sacp.web.response.ReplyInfoResponse;
import com.sacp.web.response.UserResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ForumController {

    @DubboReference(version = "1.0")
    private CourseApi courseApi;
    @DubboReference(version = "1.0")
    private ForumApi forumApi;
    @DubboReference(version = "1.0")
    private MemberApi memberApi;

    @GetMapping("getBlockInfo")
    public UserResponse getBlockInfo(@RequestParam Integer blockId){
        BlockResponse byId = forumApi.getById(blockId);
        byId.setLearnerNumber(courseApi.getCourseById(byId.getCourseId()).getLearnerNumber());
        return UserResponse.buildSuccess(byId);
    }

    @RequiresAuthentication
    @PostMapping("postPost")
    public UserResponse postPost(@RequestBody PostRequest request){
        boolean b = forumApi.post(request);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @GetMapping("getTop5")
    public UserResponse getTop5(@RequestParam Integer blockId,@RequestParam(required = false) String sacpId){
        List<PostResponse> postTop5 = forumApi.getPostTop5(blockId);
        List<PostInfoResponse> responses = new ArrayList<>(postTop5.size());
        for (PostResponse post:postTop5) {
            PostInfoResponse response = new PostInfoResponse();
            response.setPost(post);

            MemberRequest request = new MemberRequest();
            request.setSacpId(post.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(request).get(0);
            response.setMemeber(memberResponse);

            BlockResponse block = forumApi.getById(blockId);
            response.setBlock(block);

            if (sacpId!=null){
                response.setLike(forumApi.isLikePost(sacpId,post.getId()));
            }else {
                response.setLike(false);
            }

            responses.add(response);
        }
        return UserResponse.buildSuccess(responses);
    }

    @PostMapping("getPostByPage")
    public UserResponse getPostByPage(@RequestBody JSONObject page){
        Integer blockId = page.getInteger("blockId");
        Integer currentPage = page.getInteger("currentPage");
        Integer pageSize = page.getInteger("pageSize");
        String sacpId = page.getString("sacpId");

        List<PostResponse> postTop5 = forumApi.getPostByPage(blockId,currentPage,pageSize);
        List<PostInfoResponse> responses = new ArrayList<>(postTop5.size());
        for (PostResponse post:postTop5) {
            PostInfoResponse response = new PostInfoResponse();
            response.setPost(post);

            MemberRequest request = new MemberRequest();
            request.setSacpId(post.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(request).get(0);
            response.setMemeber(memberResponse);

            BlockResponse block = forumApi.getById(blockId);
            response.setBlock(block);

            if (sacpId!=null){
                response.setLike(forumApi.isLikePost(sacpId,post.getId()));
            }else {
                response.setLike(false);
            }

            responses.add(response);
        }
        return UserResponse.buildSuccess(responses);
    }

    @GetMapping("getPost")
    public UserResponse getPost(@RequestParam Integer postId){
        PostResponse postById = forumApi.getPostById(postId);

        PostInfoResponse response = new PostInfoResponse();
        response.setPost(postById);

        MemberRequest request = new MemberRequest();
        request.setSacpId(postById.getSacpId());
        MemberResponse memberResponse = memberApi.getAccount(request).get(0);
        response.setMemeber(memberResponse);

        BlockResponse block = forumApi.getById(postById.getBlockId());
        response.setBlock(block);

        CourseResponse courseById = courseApi.getCourseById(block.getCourseId());
        response.setCourse(courseById);

        return UserResponse.buildSuccess(response);
    }

    @RequiresAuthentication
    @PostMapping("replyPost")
    public UserResponse replyPost(@RequestBody ReplyRequest request){
        boolean b = forumApi.replyPost(request);
        if (b)
            return UserResponse.buildSuccess();
        else
            return UserResponse.buildFaild();
    }

    @GetMapping("getPostReply")
    public UserResponse getPostReply(@RequestParam Integer postId){
        List<ReplyResponse> replyByPostId = forumApi.getReplyByPostId(postId);

        List<ReplyInfoResponse> responses = new ArrayList<>(replyByPostId.size());
        for (ReplyResponse reply:replyByPostId) {
            ReplyInfoResponse response = new ReplyInfoResponse();
            response.setReply(reply);

            MemberRequest request = new MemberRequest();
            request.setSacpId(reply.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(request).get(0);
            response.setMember(memberResponse);

            responses.add(response);
        }
        return UserResponse.buildSuccess(responses);
    }

    @RequiresAuthentication
    @GetMapping("getReplyBySacpId")
    public UserResponse getReplyBySacpId(@RequestParam String sacpId){
        List<ReplyResponse> replyByPostId = forumApi.getReplyBySacpId(sacpId);

        List<ReplyInfoResponse> responses = new ArrayList<>(replyByPostId.size());
        for (ReplyResponse reply:replyByPostId) {
            ReplyInfoResponse response = new ReplyInfoResponse();
            response.setReply(reply);

            MemberRequest request = new MemberRequest();
            request.setSacpId(reply.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(request).get(0);
            response.setMember(memberResponse);

            responses.add(response);
        }
        return UserResponse.buildSuccess(responses);
    }

    @RequiresAuthentication
    @GetMapping("getPostBySacpId")
    public UserResponse getPostBySacpId(@RequestParam String sacpId){
        List<PostResponse> postTop5 = forumApi.getPostBySacpId(sacpId);
        List<PostInfoResponse> responses = new ArrayList<>(postTop5.size());
        for (PostResponse post:postTop5) {
            PostInfoResponse response = new PostInfoResponse();
            response.setPost(post);

            MemberRequest request = new MemberRequest();
            request.setSacpId(post.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(request).get(0);
            response.setMemeber(memberResponse);

            BlockResponse block = forumApi.getById(post.getBlockId());
            response.setBlock(block);
            responses.add(response);
        }
        return UserResponse.buildSuccess(responses);
    }

    //分页查询用户发表的内容
    @RequiresAuthentication
    @PostMapping("getPostByPage2")
    public UserResponse getPostByPage2(@RequestBody JSONObject page){
        String blockId = page.getString("sacpId");
        Integer currentPage = page.getInteger("currentPage");
        Integer pageSize = page.getInteger("pageSize");

        List<PostResponse> postTop5 = forumApi.getPostByPage2(blockId,currentPage,pageSize);
        List<PostInfoResponse> responses = new ArrayList<>(postTop5.size());
        for (PostResponse post:postTop5) {
            PostInfoResponse response = new PostInfoResponse();
            response.setPost(post);

            MemberRequest request = new MemberRequest();
            request.setSacpId(post.getSacpId());
            MemberResponse memberResponse = memberApi.getAccount(request).get(0);
            response.setMemeber(memberResponse);

            BlockResponse block = forumApi.getById(post.getBlockId());
            response.setBlock(block);
            responses.add(response);
        }
        return UserResponse.buildSuccess(responses);
    }

    @RequiresAuthentication
    @PostMapping("deletePost")
    public UserResponse deletePost(@RequestBody JSONObject request){
        String sacpId = request.getString("sacpId");
        Integer postId = request.getInteger("postId");
        PostResponse postById = forumApi.getPostById(postId);
        if (postById.getSacpId().equals(sacpId)){
            boolean b = forumApi.deletePost(postId);
            if (b)
                return UserResponse.buildSuccess("删除成功！");
            else
                return UserResponse.buildSuccess("删除失败！");
        }else {
            return UserResponse.buildSuccess("你没有权限");
        }
    }

    @RequiresAuthentication
    @PostMapping("deleteReply")
    public UserResponse deleteReply(@RequestBody JSONObject request){
        String sacpId = request.getString("sacpId");
        Integer replyId = request.getInteger("replyId");
        ReplyResponse postById = forumApi.getReplyById(replyId);
        if (postById.getSacpId().equals(sacpId)){
            boolean b = forumApi.deleteReply(replyId);
            if (b)
                return UserResponse.buildSuccess("删除成功！");
            else
                return UserResponse.buildSuccess("删除失败！");
        }else {
            return UserResponse.buildSuccess("你没有权限");
        }
    }

    @RequiresAuthentication
    @PostMapping("likePost")
    public UserResponse likePost(@RequestBody JSONObject request){
        String sacpId = request.getString("sacpId");
        Integer postId = request.getInteger("postId");
        forumApi.likePost(sacpId,postId);
        return UserResponse.buildSuccess();
    }

    @RequiresAuthentication
    @PostMapping("unLikePost")
    public UserResponse unLikePost(@RequestBody JSONObject request){
        String sacpId = request.getString("sacpId");
        Integer postId = request.getInteger("postId");
        forumApi.unLikePost(sacpId,postId);
        return UserResponse.buildSuccess();
    }

}
