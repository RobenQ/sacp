package com.sacp.course.core.repository;

import com.sacp.course.core.entity.Discussion;
import com.sacp.course.core.entity.DiscussionExample;
import com.sacp.course.core.mapper.DiscussionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public boolean updateDiscussionById(Integer discussionId){
        DiscussionExample example = new DiscussionExample();
        example.createCriteria().andIdEqualTo(discussionId);
        Discussion discussion = new Discussion();
        discussion.setIsDelete(1);
        return discussionMapper.updateByExampleSelective(discussion,example) ==1?true:false;
    }
}
