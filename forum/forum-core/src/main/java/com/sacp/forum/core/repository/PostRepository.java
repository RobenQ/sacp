package com.sacp.forum.core.repository;

import com.sacp.forum.core.entity.*;
import com.sacp.forum.core.mapper.PostLikesMapper;
import com.sacp.forum.core.mapper.PostMapper;
import com.sacp.forum.core.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PostRepository {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private PostLikesMapper postLikesMapper;

    public boolean selectLikes(String sacpId,Integer postId){
        PostLikesExample example = new PostLikesExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andPostIdEqualTo(postId);
        List<PostLikes> postLikes = postLikesMapper.selectByExample(example);
        return postLikes.size()==0?false:true;
    }

    public boolean insertPostLikes(String sacpId,Integer postId){
        PostLikes postLikes = new PostLikes();
        postLikes.setPostId(postId);
        postLikes.setSacpId(sacpId);
        postLikes.setCreateTime(new Date());
        int i = postLikesMapper.insertSelective(postLikes);
        return i==1?true:false;
    }

    public boolean deletePostLikes(String sacpId,Integer postId){
        PostLikesExample example = new PostLikesExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andPostIdEqualTo(postId);
        int i = postLikesMapper.deleteByExample(example);
        return i==1?true:false;
    }

    public boolean addOnePostLike(Integer postId){
        PostWithBLOBs byId = this.getById(postId);
        PostWithBLOBs post = new PostWithBLOBs();
        post.setLikesNumber(byId.getLikesNumber()+1);
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(postId);
        int i = postMapper.updateByExampleSelective(post, example);
        return i==1?true:false;
    }

    public boolean subOnePostLike(Integer postId){
        PostWithBLOBs byId = this.getById(postId);
        PostWithBLOBs post = new PostWithBLOBs();
        post.setLikesNumber(byId.getLikesNumber()-1);
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(postId);
        int i = postMapper.updateByExampleSelective(post, example);
        return i==1?true:false;
    }


    public Reply selectReplyById(Integer replyId){
        ReplyExample example = new ReplyExample();
        example.createCriteria().andIdEqualTo(replyId).andIsDeleteEqualTo(0);
        List<Reply> replies = replyMapper.selectByExampleWithBLOBs(example);
        if (replies.size()==0)
            return null;
        else
            return replies.get(0);
    }

    public boolean deleteReply(Integer replyId){
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andIdEqualTo(replyId);
        Reply reply = new Reply();
        reply.setIsDelete(1);
        int i = replyMapper.updateByExampleSelective(reply,replyExample);
        return i==1?true:false;
    }

    public boolean deletePost(Integer postId){
        PostExample postExample = new PostExample();
        postExample.createCriteria().andIdEqualTo(postId);
        PostWithBLOBs post = new PostWithBLOBs();
        post.setIdDelete(1);
        int i = postMapper.updateByExampleSelective(post,postExample);
        return i==1?true:false;
    }

    public List<Post> getPostTop5BySacpId(String sacpId){
        return postMapper.getPostTop5BySacpId(sacpId);
    }

    public List<Reply> selectReplyByPostId(Integer postId){
        ReplyExample example = new ReplyExample();
        example.createCriteria().andPostIdEqualTo(postId).andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return replyMapper.selectByExampleWithBLOBs(example);
    }

    public List<Reply> selectReplyBySacpId(String sacpId){
        ReplyExample example = new ReplyExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return replyMapper.selectByExampleWithBLOBs(example);
    }

    public boolean updateReplyer(Integer postId){
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(postId);
        PostWithBLOBs post = new PostWithBLOBs();
        post.setReplyNumber(this.getById(postId).getReplyNumber()+1);
        int i = postMapper.updateByExampleSelective(post, example);
        return i==1?true:false;
    }

    public boolean insertReply(Reply reply){
        int i = replyMapper.insertSelective(reply);
        return i==1?true:false;
    }

    public boolean updateViewer(Integer postId){
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(postId);
        PostWithBLOBs post = new PostWithBLOBs();
        post.setViewerNumber(this.getById(postId).getViewerNumber()+1);
        int i = postMapper.updateByExampleSelective(post, example);
        return i==1?true:false;
    }

    public PostWithBLOBs getById(Integer postId){
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(postId).andIdDeleteEqualTo(0);
        List<PostWithBLOBs> postWithBLOBs = postMapper.selectByExampleWithBLOBs(example);
        return postWithBLOBs.get(0);
    }

    public List<Post> getTop5(Integer blockId){
        return postMapper.getPostTop5(blockId);
    }

    public boolean insertPost(PostWithBLOBs post){
        int i = postMapper.insertSelective(post);
        return i==1?true:false;
    }

    public List<Post> getPostByPage(Integer blockId,Integer currentPage,Integer pageSize){
        Integer start = (currentPage-1)*pageSize+5;
        return postMapper.getPostByPage(blockId, start, pageSize);
    }

    public List<Post> getPostByPage2(String sacpId,Integer currentPage,Integer pageSize){
        Integer start = (currentPage-1)*pageSize+5;
        return postMapper.getPostByPage2(sacpId, start, pageSize);
    }
}
