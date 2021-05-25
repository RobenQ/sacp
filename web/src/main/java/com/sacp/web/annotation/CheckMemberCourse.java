package com.sacp.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouqing
 * @date 2021.04.18
 * 校验用户是否关注了某个课程，配合aop使用
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckMemberCourse {
    String value() default "";
}
