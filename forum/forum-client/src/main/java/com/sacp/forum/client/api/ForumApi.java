package com.sacp.forum.client.api;

import com.sacp.forum.client.request.BlockRequest;
import com.sacp.forum.client.response.BlockResponse;

public interface ForumApi {
    //block
    public Integer createBlock(BlockRequest request);
    public boolean fullBlockCourseId(Integer blockId,Integer courseId);
    public BlockResponse getById(Integer id);
}
