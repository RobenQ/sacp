package com.sacp.forum.client.api;

import com.sacp.forum.client.request.BlockRequest;
import com.sacp.forum.client.request.PostRequest;
import com.sacp.forum.client.request.ReplyRequest;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.client.response.PostResponse;
import com.sacp.forum.client.response.ReplyResponse;

import java.util.List;

/**
 * 社区内容管理dubbo接口
 * 一个课程对应一个版块，帖子内容回复不支持二级回复
 * @author zhouqing
 * @date 2021/04/02
 */
public interface ForumApi {
    //block====================================================================

    /**
     * 创建版块，一般是用户在创建课程时调用，创建课程同时也创建了一个版块
     * @param request
     * @return 创建的版块的版块ID
     */
    public Integer createBlock(BlockRequest request);

    /**
     * 填充版块的课程ID字段
     * @param blockId 版块ID
     * @param courseId 课程ID
     * @return 是否填充成功
     */
    public boolean fullBlockCourseId(Integer blockId,Integer courseId);

    /**
     * 根据ID获取版块信息
     * @param id 版块ID
     * @return 获取的版块信息
     */
    public BlockResponse getById(Integer id);

    /**
     * 获取热门版块
     * @return 热门版块列表
     */
    public List<ReplyResponse> getHotBlock();

    /**
     * 删除版块
     * @param courseId 版块对应的课程ID
     * @return
     */
    public boolean deleteBlockByAuthor(Integer courseId);

    public boolean recoveryBlockByAuthor(Integer courseId);


    //memberBlock===================================================================

    /**
     * 加入版块，一般用户加入课程时就会调用该方法加入版块
     * @param blockId 版块ID
     * @param sacpId 用户账户sacpId
     * @return 是否加入成功
     */
    public boolean joinBlock(Integer blockId,String sacpId);

    /**
     * 退出版块
     * @param blockId 要退出的版块ID
     * @param sacpId 用户账户的sacpId
     * @return 是否退出成功
     */
    public boolean outBlock(Integer blockId,String sacpId);

    /**
     * 获取用户最新关注的前10个版块
     * @param sacpId 用户账户sacpId
     * @return 用户最新关注的版块信息列表
     */
    public List<BlockResponse> getMbBySacpIdLimit(String sacpId);


    //post=====================================================================

    /**
     * 发表帖子
     * @param request 发表帖子请求对象
     * @return 是否发表成功
     */
    public boolean post(PostRequest request);

    /**
     * 根据版块ID查询最新的5个帖子
     * @param blockId 版块ID
     * @return 最新的前5个帖子的信息列表
     */
    public List<PostResponse> getPostTop5(Integer blockId);

    /**
     * 获取最新的20个帖子
     * @return
     */
    public List<PostResponse> getNewPostTop20();

    /**
     * 根据版块ID分页查询帖子
     * @param blockId 版块ID
     * @param currentPage 当前页数
     * @param pageSize 每页显示的数量
     * @return 该页的帖子信息列表
     */
    public List<PostResponse> getPostByPage(Integer blockId,Integer currentPage,Integer pageSize);

    /**
     * 根据用户账户sacpId分页查询帖子
     * @param sacpId 用户账户sacpId
     * @param currentPage 当前页数
     * @param pageSize 每页显示的数量
     * @return 该用户的帖子信息列表
     */
    public List<PostResponse> getPostByPage2(String sacpId,Integer currentPage,Integer pageSize);

    /**
     * 获取用户最新发布的前5个帖子
     * @param sacpId 用户账户sacpId
     * @return 查询的帖子信息列表
     */
    public List<PostResponse> getPostBySacpId(String sacpId);

    /**
     * 根据帖子ID获取帖子信息
     * @param postId 帖子ID
     * @return 查询的帖子信息
     */
    public PostResponse getPostById(Integer postId);

    /**
     * 回复帖子
     * @param request 回复帖子请求对象
     * @return 是否回复成功
     */
    public boolean replyPost(ReplyRequest request);

    /**
     * 获取帖子的回复
     * @param postId 帖子ID
     * @return 帖子的回复信息列表
     */
    public List<ReplyResponse> getReplyByPostId(Integer postId);

    /**
     * 根据回复ID回去回复
     * @param replyId 回复ID
     * @return 查询的回复信息
     */
    public ReplyResponse getReplyById(Integer replyId);

    /**
     * 获取用户的回复
     * @param sacpId 用户账户sacpId
     * @return 用户的回复信息列表
     */
    public List<ReplyResponse> getReplyBySacpId(String sacpId);

    /**
     * 删除帖子
     * @param postId 帖子ID
     * @return 是否删除成功
     */
    public boolean deletePost(Integer postId);

    /**
     * 删除回复
     * @param replyId 回复ID
     * @return 是否删除成功
     */
    public boolean deleteReply(Integer replyId);


    //post like===================================================================
    public boolean likePost(String sacpId,Integer postId);

    public boolean unLikePost(String sacpId,Integer postId);

    public boolean isLikePost(String sacpId,Integer postId);

    //reply like===================================================================
    public boolean likeReply(String sacpId,Integer replyId);

    public boolean unLikeReply(String sacpId,Integer replyId);

    public boolean isLikeReply(String sacpId,Integer replyId);

    public long countPost();

}
