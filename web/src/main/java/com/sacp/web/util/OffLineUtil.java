package com.sacp.web.util;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouqing
 * @date 2021.04.18
 * 强制下线会话管理工具
 */
@Component
public class OffLineUtil {
    private final Object object = new Object();
    private static ConcurrentHashMap<String,Object> offLineUserMap = new ConcurrentHashMap();

    public void addOffLineUser(String nickName){
        offLineUserMap.put(nickName,object);
    }

    public void removeOffLineUser(Subject subject){
        offLineUserMap.remove((String) subject.getPrincipal());
    }

    public boolean hasSubject(Subject subject){
        if ((String) subject.getPrincipal()==null)
            return false;
        return offLineUserMap.containsKey((String) subject.getPrincipal());
    }

    public void logout(Subject subject){
        offLineUserMap.remove((String) subject.getPrincipal());
        subject.logout();
    }
}
