package com.sacp.forum.core.repository;

import com.alibaba.fastjson.JSON;
import com.sacp.forum.core.entity.BlockInfo;
import com.sacp.forum.core.entity.BlockInfoExample;
import com.sacp.forum.core.mapper.BlockInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockRepository {
    @Autowired
    private BlockInfoMapper blockInfoMapper;

    public boolean insertBlock(BlockInfo blockInfo){
        int i = blockInfoMapper.insertSelective(blockInfo);
        return i==1?true:false;
    }

    public boolean updateBlockCourseId(Integer blockId,Integer courseId){
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.setId(blockId);
        blockInfo.setCourseId(courseId);
        BlockInfoExample example = new BlockInfoExample();
        example.createCriteria().andIdEqualTo(blockId);
        int i = blockInfoMapper.updateByExampleSelective(blockInfo,example);
        return i==1?true:false;
    }

    public BlockInfo selectById(Integer id){
        BlockInfoExample example = new BlockInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return blockInfoMapper.selectByPrimaryKey(id);
    }
}
