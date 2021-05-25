package com.sacp.forum.core.repository;

import com.alibaba.fastjson.JSON;
import com.sacp.forum.core.entity.*;
import com.sacp.forum.core.mapper.BlockInfoMapper;
import com.sacp.forum.core.mapper.MemberBlockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BlockRepository {
    @Autowired
    private BlockInfoMapper blockInfoMapper;
    @Autowired
    private MemberBlockMapper memberBlockMapper;


    public List<MemberBlock> getMbBySqcpId(String sacpId){
        return memberBlockMapper.getMbBySacpIdLimit(sacpId);
    }

    public MemberBlock getMbByBlockIdAndSacpId(Integer blockId,String sacpId){
        MemberBlockExample example = new MemberBlockExample();
        example.createCriteria().andBlockIdEqualTo(blockId).andSacpIdEqualTo(sacpId);
        List<MemberBlock> memberBlocks = memberBlockMapper.selectByExample(example);
        if (memberBlocks.size()!=0)
            return memberBlocks.get(0);
        else
            return null;
    }

    public boolean insertMb(Integer blockId,String sacpId){
        MemberBlock memberBlock = new MemberBlock();
        memberBlock.setBlockId(blockId);
        memberBlock.setSacpId(sacpId);
        memberBlock.setCreateTime(new Date());
        int i = memberBlockMapper.insertSelective(memberBlock);
        return i==1?true:false;
    }

    public boolean deleteMb(Integer blockId,String sacpId){
        MemberBlockExample example = new MemberBlockExample();
        example.createCriteria().andBlockIdEqualTo(blockId).andSacpIdEqualTo(sacpId);
        memberBlockMapper.deleteByExample(example);
        return true;
    }

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

    public boolean deleteBlockCourseId(Integer courseId){
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.setIsDelete(1);
        BlockInfoExample example = new BlockInfoExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        int i = blockInfoMapper.updateByExampleSelective(blockInfo,example);
        return i==1?true:false;
    }

    public BlockInfo selectById(Integer id){
        BlockInfoExample example = new BlockInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return blockInfoMapper.selectByPrimaryKey(id);
    }
}
