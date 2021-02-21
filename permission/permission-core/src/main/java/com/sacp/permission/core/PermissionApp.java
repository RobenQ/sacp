package com.sacp.permission.core;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sacp.permission.core.mapper")
@EnableDubbo
public class PermissionApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(PermissionApp.class,args);
    }
}
