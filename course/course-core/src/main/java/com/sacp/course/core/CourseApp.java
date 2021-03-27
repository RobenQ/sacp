package com.sacp.course.core;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sacp.course.core.mapper")
@EnableDubbo
public class CourseApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(CourseApp.class,args);
    }
}
