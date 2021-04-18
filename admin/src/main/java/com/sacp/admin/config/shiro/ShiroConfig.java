package com.sacp.admin.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public MyWebSessionManager myWebSessionManager(){
        MyWebSessionManager defaultSessionManager = new MyWebSessionManager();
        defaultSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultSessionManager;
    }

    /**
     * 在spring中注入自定义的认证filter
     * @return
     */
    @Bean
    public MyAuthFilter myAuthFilter(){
        return new MyAuthFilter();
    }

    /**
     * 注入shiro默认的filterFactory
     * @return
     */
    @Bean
    public MyShiroFilterFactoryBean shiroFilterFactoryBean(){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        MyShiroFilterFactoryBean shiroFilterFactoryBean = new MyShiroFilterFactoryBean();
        //使用注入的安全管理器
        shiroFilterFactoryBean.setSecurityManager(myWebSecurityManager());
        //设置登录的URL
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权提示的URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthoriz");
        //设置登录成功的URL
        shiroFilterFactoryBean.setSuccessUrl("/success");
        //将自定义的认证filter放到shiro中并命名为myauthc，这样在拦截规则设置中才可以使用
        HashMap<String, Filter> filters = new HashMap<>();
        filters.put("myauthc",new MyAuthFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //设置shiro拦截规则
        HashMap<String, String> filterChainDefinition = new LinkedHashMap<>();
//        filterChainDefinition.put("/logout","logout");
        filterChainDefinition.put("/check*","anon");
        filterChainDefinition.put("/test*","anon");
        filterChainDefinition.put("/login","anon");
        filterChainDefinition.put("/**","myauthc");
        //把过滤规则放入shiro
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        return shiroFilterFactoryBean;
    }

}
