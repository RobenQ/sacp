package com.sacp.forum.core.service;

import com.alibaba.fastjson.JSON;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.request.BlockRequest;
import com.sacp.forum.client.request.PostRequest;
import com.sacp.forum.client.request.ReplyRequest;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.client.response.PostResponse;
import com.sacp.forum.client.response.ReplyResponse;
import com.sacp.forum.core.entity.*;
import com.sacp.forum.core.repository.BlockRepository;
import com.sacp.forum.core.repository.PostRepository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(version = "1.0")
public class ForumService implements ForumApi {

    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public long countPost() {
        return postRepository.countPost();
    }

    @Override
    public boolean likeReply(String sacpId, Integer replyId) {
        postRepository.insertReplyLikes(sacpId,replyId);
        postRepository.addOneReplyLike(replyId);
        return true;
    }

    @Override
    public boolean unLikeReply(String sacpId, Integer replyId) {
        postRepository.deleteReplyLikes(sacpId,replyId);
        postRepository.subOneReplyLike(replyId);
        return true;
    }

    @Override
    public boolean isLikeReply(String sacpId, Integer replyId) {
        return postRepository.selectReplyLikes(sacpId,replyId);
    }

    @Override
    public boolean isLikePost(String sacpId, Integer postId) {
        return postRepository.selectLikes(sacpId,postId);
    }

    @Override
    public boolean likePost(String sacpId, Integer postId) {
        postRepository.insertPostLikes(sacpId,postId);
        postRepository.addOnePostLike(postId);
        return true;
    }

    @Override
    public boolean unLikePost(String sacpId, Integer postId) {
        postRepository.deletePostLikes(sacpId,postId);
        postRepository.subOnePostLike(postId);
        return true;
    }

    @Override
    public ReplyResponse getReplyById(Integer replyId) {
        Reply reply = postRepository.selectReplyById(replyId);
        if (reply==null)
            return null;
        ReplyResponse response = new ReplyResponse();
        BeanUtils.copyProperties(reply,response);
        return response;
    }

    @Override
    public boolean deleteReply(Integer replyId) {
        return postRepository.deleteReply(replyId);
    }

    @Override
    public boolean recoveryReply(Integer replyId) {
        return postRepository.recoveryReply(replyId);
    }

    @Override
    public List<PostResponse> getPost(PostRequest request, List<Date> timeRange) {
        Post post = new Post();
        BeanUtils.copyProperties(request,post);
        if (timeRange.size()<2){
            timeRange.add(0,null);
            timeRange.add(1,null);
        }
        List<PostWithBLOBs> posts = postRepository.getPost(post,
                timeRange.get(0), timeRange.get(1));
        List<PostResponse> responses = new ArrayList<>(posts.size());
        for (PostWithBLOBs post1:posts) {
            PostResponse response = new PostResponse();
            BeanUtils.copyProperties(post1,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public List<ReplyResponse> getPostReply(ReplyRequest request, List<Date> timeRange) {
        Reply reply = new Reply();
        BeanUtils.copyProperties(request,reply);
        if (timeRange.size()<2){
            timeRange.add(0,null);
            timeRange.add(1,null);
        }
        List<Reply> replies = postRepository.getPostReply(reply,
                timeRange.get(0), timeRange.get(1));
        List<ReplyResponse> responses = new ArrayList<>(replies.size());
        for (Reply reply1:replies) {
            ReplyResponse response = new ReplyResponse();
            BeanUtils.copyProperties(reply1,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public boolean deletePost(Integer postId) {
        return postRepository.deletePost(postId);
    }

    @Override
    public boolean recoveryPost(Integer postId) {
        return postRepository.recoveryPost(postId);
    }

    @Override
    public List<PostResponse> getPostBySacpId(String sacpId) {
        List<Post> top5 = postRepository.getPostTop5BySacpId(sacpId);
        List<PostResponse> responses = new ArrayList<>(top5.size());
        for (Post post:top5) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(post,postResponse);
            responses.add(postResponse);
        }
        return responses;
    }

    @Override
    public List<ReplyResponse> getReplyByPostId(Integer postId) {
        List<Reply> replies = postRepository.selectReplyByPostId(postId);
        List<ReplyResponse> responses = new ArrayList<>(replies.size());
        for (Reply reply:replies) {
            ReplyResponse response = new ReplyResponse();
            BeanUtils.copyProperties(reply,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public List<ReplyResponse> getReplyBySacpId(String sacpId) {
        List<Reply> replies = postRepository.selectReplyBySacpId(sacpId);
        List<ReplyResponse> responses = new ArrayList<>(replies.size());
        for (Reply reply:replies) {
            ReplyResponse response = new ReplyResponse();
            BeanUtils.copyProperties(reply,response);
            responses.add(response);
        }
        return responses;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean replyPost(ReplyRequest request) {
        Reply reply = new Reply();
        BeanUtils.copyProperties(request,reply);
        reply.setCreateTime(new Date());
        postRepository.updateReplyer(reply.getPostId());
        return postRepository.insertReply(reply);
    }

    @Override
    public PostResponse getPostById(Integer postId) {
        PostWithBLOBs byId = postRepository.getById(postId);
        postRepository.updateViewer(postId);
        PostResponse post = new PostResponse();
        post.setId(byId.getId());
        post.setSacpId(byId.getSacpId());
        post.setBlockId(byId.getBlockId());
        post.setTitle(byId.getTitle());
        post.setTxt(byId.getTxt());
        post.setContext(byId.getContext());
        post.setClassifyId(byId.getClassifyId());
        post.setOrders(byId.getOrders());
        post.setViewerNumber(byId.getViewerNumber());
        post.setReplyNumber(byId.getReplyNumber());
        post.setLikesNumber(byId.getLikesNumber());
        post.setCreateTime(byId.getCreateTime());
        post.setIdDelete(byId.getIdDelete());
        return post;
    }

    @Override
    public PostResponse getPostById2(Integer postId) {
        PostWithBLOBs byId = postRepository.getById2(postId);
        PostResponse post = new PostResponse();
        post.setId(byId.getId());
        post.setSacpId(byId.getSacpId());
        post.setBlockId(byId.getBlockId());
        post.setTitle(byId.getTitle());
        post.setTxt(byId.getTxt());
        post.setContext(byId.getContext());
        post.setClassifyId(byId.getClassifyId());
        post.setOrders(byId.getOrders());
        post.setViewerNumber(byId.getViewerNumber());
        post.setReplyNumber(byId.getReplyNumber());
        post.setLikesNumber(byId.getLikesNumber());
        post.setCreateTime(byId.getCreateTime());
        post.setIdDelete(byId.getIdDelete());
        return post;
    }

    @Override
    public List<PostResponse> getPostByPage(Integer blockId, Integer currentPage, Integer pageSize) {
        List<Post> postByPage = postRepository.getPostByPage(blockId, currentPage, pageSize);
        List<PostResponse> responses = new ArrayList<>(postByPage.size());
        for (Post post:postByPage) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(post,postResponse);
            responses.add(postResponse);
        }
        return responses;
    }

    @Override
    public List<PostResponse> getPostByPage2(String sacpId, Integer currentPage, Integer pageSize) {
        List<Post> postByPage = postRepository.getPostByPage2(sacpId, currentPage, pageSize);
        List<PostResponse> responses = new ArrayList<>(postByPage.size());
        for (Post post:postByPage) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(post,postResponse);
            responses.add(postResponse);
        }
        return responses;
    }

    @Override
    public List<PostResponse> getPostTop5(Integer blockId) {
        List<Post> top5 = postRepository.getTop5(blockId);
        List<PostResponse> responses = new ArrayList<>(top5.size());
        for (Post post:top5) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(post,postResponse);
            responses.add(postResponse);
        }
        return responses;
    }

    @Override
    public List<PostResponse> getNewPostTop20() {
        List<Post> top20 = postRepository.getNewTop20();
        List<PostResponse> responses = new ArrayList<>(top20.size());
        for (Post post:top20) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(post,postResponse);
            responses.add(postResponse);
        }
        return responses;
    }

    @Override
    public boolean post(PostRequest request) {
        PostWithBLOBs post2 = new PostWithBLOBs();
        post2.setSacpId(request.getSacpId());
        post2.setBlockId(request.getBlockId());
        post2.setTxt(request.getTxt());
        post2.setContext(request.getContext());
        post2.setClassifyId(request.getClassifyId());
        post2.setCreateTime(new Date());
        return postRepository.insertPost(post2);
    }

    @Override
    public List<BlockResponse> getMbBySacpIdLimit(String sacpId) {
        List<MemberBlock> mbBySqcpId = blockRepository.getMbBySqcpId(sacpId);
        List<BlockResponse> blockResponses = new ArrayList<>(mbBySqcpId.size());
        for (MemberBlock mb:mbBySqcpId) {
            BlockResponse byId = this.getById(mb.getBlockId());
            blockResponses.add(byId);
        }
        return blockResponses;
    }

    @Override
    public boolean joinBlock(Integer blockId, String sacpId) {
        MemberBlock memberBlock = blockRepository.getMbByBlockIdAndSacpId(blockId,sacpId);
        if (memberBlock==null)
            return blockRepository.insertMb(blockId,sacpId);
        else
            return true;
    }

    @Override
    public boolean outBlock(Integer blockId, String sacpId) {
        return blockRepository.deleteMb(blockId,sacpId);
    }

    @Override
    public BlockResponse getById(Integer id) {
        BlockResponse response = new BlockResponse();
        BlockInfo blockInfo = blockRepository.selectById(id);
        BeanUtils.copyProperties(blockInfo,response);
        return response;
    }

    @Override
    public List<ReplyResponse> getHotBlock() {
        return null;
    }

    @Override
    public boolean deleteBlockByAuthor(Integer courseId) {
        return blockRepository.deleteBlockCourseId(courseId);
    }

    @Override
    public boolean recoveryBlockByAuthor(Integer courseId) {
        return blockRepository.recoveryBlockCourseId(courseId);
    }

    @Override
    public Integer createBlock(BlockRequest request) {
        BlockInfo blockInfo = new BlockInfo();
        BeanUtils.copyProperties(request,blockInfo);
        blockInfo.setIsDelete(0);
        blockRepository.insertBlock(blockInfo);
        return blockInfo.getId();
    }

    @Override
    public boolean fullBlockCourseId(Integer blockId,Integer courseId) {
        return blockRepository.updateBlockCourseId(blockId,courseId);
    }
}
