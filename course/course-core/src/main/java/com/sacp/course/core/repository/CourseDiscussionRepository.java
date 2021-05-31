package com.sacp.course.core.repository;

import com.sacp.course.core.entity.Discussion;
import com.sacp.course.core.entity.DiscussionExample;
import com.sacp.course.core.mapper.DiscussionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CourseDiscussionRepository {
    @Autowired
    private DiscussionMapper discussionMapper;

    public boolean insertDiscussion(Discussion discussion){
        int i = discussionMapper.insertSelective(discussion);
        return i==1?true:false;
    }

    public List<Discussion> getReplyByCourseId(Integer courseId){
        DiscussionExample example = new DiscussionExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andIsDeleteEqualTo(0);
        return discussionMapper.selectByExampleWithBLOBs(example);
    }

    public List<Discussion> getAllDiscussionBySacpId(String sacpId){
        DiscussionExample example = new DiscussionExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andIsDeleteEqualTo(0);
        return discussionMapper.selectByExampleWithBLOBs(example);
    }

    public List<Discussion> getDiscussion(Discussion discussion, Date startTime,Date endTime){
        DiscussionExample example = new DiscussionExample();
        DiscussionExample.Criteria criteria = example.createCriteria();
        if (discussion.getId()!=null && discussion.getId()!=0)
            criteria.andIdEqualTo(discussion.getId());
        if (discussion.getCourseId()!=null && discussion.getCourseId()!=0)
            criteria.andCourseIdEqualTo(discussion.getCourseId());
        if (discussion.getMemberNickname()!=null)
            criteria.andMemberNicknameEqualTo(discussion.getMemberNickname());
        if (startTime!=null && endTime!=null)
            criteria.andCreateTimeBetween(startTime,endTime);
        return discussionMapper.selectByExampleWithBLOBs(example);
    }

    public boolean updateDiscussionById(Integer discussionId){
        DiscussionExample example = new DiscussionExample();
        example.createCriteria().andIdEqualTo(discussionId);
        Discussion discussion = new Discussion();
        discussion.setIsDelete(1);
        return discussionMapper.updateByExampleSelective(discussion,example) ==1?true:false;
    }

    public boolean recoveryDiscussionById(Integer discussionId){
        DiscussionExample example = new DiscussionExample();
        example.createCriteria().andIdEqualTo(discussionId);
        Discussion discussion = new Discussion();
        discussion.setIsDelete(0);
        return discussionMapper.updateByExampleSelective(discussion,example) ==1?true:false;
    }
}
