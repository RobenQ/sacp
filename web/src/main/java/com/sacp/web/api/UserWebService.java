package com.sacp.web.api;

import com.sacp.web.client.api.UserWebApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@DubboService(version = "1.0")
public class UserWebService implements UserWebApi {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public long allActiveUser() {
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        for (Session s:activeSessions) {
            System.out.println(s.getHost());
            System.out.println(s.getAttribute(DefaultWebSubjectContext.PRINCIPALS_SESSION_KEY));
        }
        return 0;
    }
}
