package com.sacp.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS跨域设置，这里没有使用spring的设置方式，因为shiro的过滤器
 * 优先于spring CORS过滤器，会在spring　CORS配置之前将请求拦截，
 * 而shiro并没有设置跨域，结果导致跨域失效。所以这里采用实现Filter
 * 接口的方式实现CORS，使得CORS过滤器优先于shiro的过滤器。
 */
@Configuration
@Slf4j
public class CorsConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response2 = (HttpServletResponse) response;
        HttpServletRequest request2 = (HttpServletRequest) request;
        //放行所有,类似*,这里*无效
        response2.setHeader("Access-Control-Allow-Origin", request2.getHeader("Origin"));
        response2.setHeader("Access-Control-Allow-Credentials", "true");
        //允许请求方式
        response2.setHeader("Access-Control-Allow-Methods", request2.getMethod());
        response2.setHeader("Access-Control-Max-Age", "3600");
        //需要放行header头部字段 如需鉴权字段，自行添加，如Authorization
        response2.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Sacp_Token");
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("CORS过滤器放行异常:",e);
        }
    }

    @Override
    public void destroy() {

    }
}
