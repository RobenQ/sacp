package com.sacp.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sacp.admin.response.AdminResponse;
import com.sacp.course.client.api.CourseApi;
import com.sacp.course.client.request.CourseClassifyRequest;
import com.sacp.course.client.response.CourseClassifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CourseController {

    @DubboReference(version = "1.0")
    private CourseApi courseApi;

    @GetMapping("getallclassify")
    public AdminResponse getAllClassify() {
        List<CourseClassifyResponse> allClassify = courseApi.getAllClassify();
        return AdminResponse.buildSuccess("查询全部课程成功",allClassify);
    }

    @PostMapping("addclassify")
    public AdminResponse addClassify(@RequestBody JSONObject courseClassify){
        List<CourseClassifyResponse> classifyName = courseApi.getClassifyByName(courseClassify.getString("classifyName"));
        if (classifyName.size()!=0)
            return AdminResponse.buildFaild("课程类别名称重复!");
        CourseClassifyRequest courseClassify2 = new CourseClassifyRequest();
        courseClassify2.setClassifyName(courseClassify.getString("classifyName"));
        courseClassify2.setDescr(courseClassify.getString("descr"));
        boolean classify = courseApi.createClassify(courseClassify2);
        if (classify)
            return AdminResponse.buildSuccess("添加成功！"
                    , courseApi.getClassifyByName(courseClassify.getString("classifyName")).get(0));
        else
            return AdminResponse.buildFaild("添加失败!");
    }

    @PostMapping("updateclassify")
    public AdminResponse updateClassify(@RequestBody CourseClassifyRequest request){
        boolean res = courseApi.modifyClassify(request);
        if (courseApi.getClassifyByName(request.getClassifyName())!=null)
            return AdminResponse.buildFaild("修改失败!");
        if (res)
            return AdminResponse.buildSuccess("修改成功！");
        else
            return AdminResponse.buildFaild("修改失败!");
    }

    @PostMapping("getclassify")
    public AdminResponse getClassify(@RequestBody CourseClassifyRequest request){
        List<CourseClassifyResponse> classifys = courseApi.getClassify(request);
        return AdminResponse.buildSuccess(classifys);
    }
}
