package com.sacp.forum.core.service;

import com.alibaba.fastjson.JSON;
import com.sacp.forum.client.api.ForumApi;
import com.sacp.forum.client.request.BlockRequest;
import com.sacp.forum.client.response.BlockResponse;
import com.sacp.forum.core.entity.BlockInfo;
import com.sacp.forum.core.entity.MemberBlock;
import com.sacp.forum.core.repository.BlockRepository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0")
public class ForumService implements ForumApi {

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public boolean joinBlock(Integer blockId, String sacpId) {
        MemberBlock memberBlock = blockRepository.getMbByBlockIdAndSacpId(blockId,sacpId);
        if (memberBlock==null)
            return blockRepository.insertMb(blockId,sacpId);
        else
            return true;
    }

    @Override
    public BlockResponse getById(Integer id) {
        BlockResponse response = new BlockResponse();
        BlockInfo blockInfo = blockRepository.selectById(id);
        BeanUtils.copyProperties(blockInfo,response);
        return response;
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
