package com.sacp.web.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * shiro配置
 */
@Configuration
public class ShiroConfig {

    /**
     * 在spring中注入shiro默认的安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager myWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //使用自定义的realm
        securityManager.setRealm(myRealm());
        //并使用自定义的会话管理器管理会话
        securityManager.setSessionManager(myWebSessionManager());
        return securityManager;
    }

    /**
     * 在spring中注入自定义的realm
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    /**
     * 在spring中注入自定义的会话管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager myWebSessionManager(){
//        MyWebSessionManager defaultSessionManager = new MyWebSessionManager();
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        defaultWebSessionManager.setGlobalSessionTimeout(600000L);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        return defaultWebSessionManager;
    }

    /**
     * 注入shiro默认的filterFactory
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        MyShiroFilterFactoryBean shiroFilterFactoryBean = new MyShiroFilterFactoryBean();
        //使用注入的安全管理器
        shiroFilterFactoryBean.setSecurityManager(myWebSecurityManager());
        //设置登录的URL
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权提示的URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthoriz");
        //设置登录成功的URL
        shiroFilterFactoryBean.setSuccessUrl("/success");
        //设置shiro拦截规则
        HashMap<String, String> filterChainDefinition = new LinkedHashMap<>();
//        filterChainDefinition.put("/templates/**","anon");
//        filterChainDefinition.put("/static/**","anon");
//        filterChainDefinition.put("/checkok","anon");
//        filterChainDefinition.put("/login","anon");
//        filterChainDefinition.put("/logout","logout");
//        filterChainDefinition.put("/**","authc");
        //把过滤规则放入shiro
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        return shiroFilterFactoryBean;
    }

    /**
     * 极其重要，不加入此配置，controller中的方法如果加了shiro的注解，并且项目中导入了aop的包，
     * 会导致整个controller的url报404
     * @return
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);

        return defaultAdvisorAutoProxyCreator;
    }

}
