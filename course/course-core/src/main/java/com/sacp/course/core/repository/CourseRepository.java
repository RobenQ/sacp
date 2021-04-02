package com.sacp.course.core.repository;

import com.sacp.course.core.entity.*;
import com.sacp.course.core.mapper.CourseInfoMapper;
import com.sacp.course.core.mapper.MemberCourseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CourseRepository {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Autowired
    private MemberCourseMapper memberCourseMapper;

    public List<CourseInfo> getHot5(){
        return courseInfoMapper.getHot5();
    }

    public List<CourseInfo> getNew5(){
        return courseInfoMapper.getNew5();
    }

    public boolean deleteMcNoFact(String sacpId,Integer courseId){
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andCourseIdEqualTo(courseId);
        List<MemberCourse> memberCourses = memberCourseMapper.selectByExample(example);
        if (memberCourses.size()==0)
            return true;
        else {
            MemberCourse memberCourse = new MemberCourse();
            memberCourse.setIsDelete(1);
            example.clear();
            example.createCriteria().andIdEqualTo(memberCourses.get(0).getId());
            int i = memberCourseMapper.updateByExampleSelective(memberCourse, example);
            return i==1?true:false;
        }
    }

    public List<MemberCourse> getMcByPage(String sacpId,int pagesize,int currentPage){
        int start = (currentPage-1)*pagesize;
        return memberCourseMapper.selectBySacpIdAndPage(sacpId,start,pagesize);
    }

    public long getMcTotalPage(String sacpId){
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria().andSacpIdEqualTo(sacpId);
        List<MemberCourse> memberCourses = memberCourseMapper.selectByExample(example);
        long res = memberCourses.size();
        for (MemberCourse mc:memberCourses) {
            CourseInfo courseById = this.getCourseById(mc.getCourseId());
            if (courseById.getIsDelete()==1)
                res--;
        }
        return res;
    }

    public boolean addOneLearnerNum(Integer courseId){
        CourseInfo courseById = this.getCourseById(courseId);

        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setLearnerNumber(courseById.getLearnerNumber()+1);

        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andIdEqualTo(courseId);
        int i = courseInfoMapper.updateByExampleSelective(courseInfo, example);
        return i==1?true:false;
    }

    public boolean subOneLearnerNum(Integer courseId){
        CourseInfo courseById = this.getCourseById(courseId);
        CourseInfo courseInfo = new CourseInfo();
        if (courseById.getLearnerNumber()>=1)
            courseInfo.setLearnerNumber(courseById.getLearnerNumber()-1);
        else
            courseInfo.setLearnerNumber(0);
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andIdEqualTo(courseId);
        int i = courseInfoMapper.updateByExampleSelective(courseInfo, example);
        return i==1?true:false;
    }

    public boolean addOneReplyNum(Integer courseId){
        CourseInfo courseById = this.getCourseById(courseId);
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setReplyNumber(courseById.getReplyNumber()+1);
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andIdEqualTo(courseId);
        int i = courseInfoMapper.updateByExampleSelective(courseInfo, example);
        return i==1?true:false;
    }

    public MemberCourse getMcByCourseIdAndSacpId(Integer courseId,String sacpId){
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andSacpIdEqualTo(sacpId);
        List<MemberCourse> memberCourses = memberCourseMapper.selectByExample(example);
        if (memberCourses.size()!=0)
            return memberCourses.get(0);
        else
            return null;
    }

    public boolean updateMcStatus(Integer courseId,String sacpId){
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andSacpIdEqualTo(sacpId);
        MemberCourse memberCourse = new MemberCourse();
        memberCourse.setIsDelete(0);
        int i = memberCourseMapper.updateByExampleSelective(memberCourse, example);
        return i==1?true:false;
    }

    public boolean insertMc(Integer courseId,String sacpId){
        MemberCourse memberCourse = new MemberCourse();
        memberCourse.setCourseId(courseId);
        memberCourse.setSacpId(sacpId);
        memberCourse.setCreateTime(new Date());
        memberCourse.setIsDelete(0);
        int i = memberCourseMapper.insertSelective(memberCourse);
        return i==1?true:false;
    }

    public List<CourseInfo> getAllCourse(){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andIdIsNotNull().andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseInfoMapper.selectByExample(example);
    }

    public List<CourseInfo> getCourseBySacpId(String sacpId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseInfoMapper.selectByExample(example);
    }

    public List<CourseInfo> getCourseByClassifyId(Integer classifyId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andClassifyIdEqualTo(classifyId).andIsDeleteEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseInfoMapper.selectByExample(example);
    }

    public CourseInfo getCourseById(Integer id){
        CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(id);
        return courseInfo;
    }

    public boolean insertCourse(CourseInfo courseInfo){
        int i = courseInfoMapper.insertSelective(courseInfo);
        return i==1?true:false;
    }

    public long countBySacpId(String sacpId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andSacpIdEqualTo(sacpId).andIsDeleteEqualTo(0);
        long l = courseInfoMapper.countByExample(example);
        return l;
    }

    public long countByClassifyId(Integer classifyId){
        CourseInfoExample example = new CourseInfoExample();
        example.createCriteria().andClassifyIdEqualTo(classifyId).andIsDeleteEqualTo(0);
        long l = courseInfoMapper.countByExample(example);
        return l;
    }

    public List<CourseInfo> getCourseByPage(String sacpId,int pagesize,int currentPage){
        int start = (currentPage-1)*pagesize;
        return courseInfoMapper.selectByPage(sacpId,start,pagesize);
    }

    public List<CourseInfo> getCourseByPage(Integer classifyId,int pagesize,int currentPage){
        int start = (currentPage-1)*pagesize;
        return courseInfoMapper.selectByClassifyAndPage(classifyId,start,pagesize);
    }
}
