package com.sacp.forum.client.api;

import com.sacp.forum.client.request.BlockRequest;
import com.sacp.forum.client.request.PostRequest;
import com.sacp.forum.client.request.ReplyRequest;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.client.response.PostResponse;
import com.sacp.forum.client.response.ReplyResponse;

import java.util.List;

public interface ForumApi {
    //block
    public Integer createBlock(BlockRequest request);
    public boolean fullBlockCourseId(Integer blockId,Integer courseId);
    public BlockResponse getById(Integer id);


    //memberBlock
    public boolean joinBlock(Integer blockId,String sacpId);
    public boolean outBlock(Integer blockId,String sacpId);
    public List<BlockResponse> getMbBySacpIdLimit(String sacpId);


    //post
    public boolean post(PostRequest request);
    public List<PostResponse> getPostTop5(Integer blockId);
    public List<PostResponse> getPostByPage(Integer blockId,Integer currentPage,Integer pageSize);
    public List<PostResponse> getPostByPage2(String sacpId,Integer currentPage,Integer pageSize);
    public List<PostResponse> getPostBySacpId(String sacpId);
    public PostResponse getPostById(Integer postId);
    public boolean replyPost(ReplyRequest request);
    public List<ReplyResponse> getReplyByPostId(Integer postId);
    public ReplyResponse getReplyById(Integer replyId);
    public List<ReplyResponse> getReplyBySacpId(String sacpId);
    public boolean deletePost(Integer postId);
    public boolean deleteReply(Integer replyId);
}
