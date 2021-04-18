package com.sacp.web.api;

import com.sacp.member.client.api.MemberApi;
import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.MemberResponse;
import com.sacp.web.client.api.SacpWebApi;
import com.sacp.web.client.response.ActiveUserResponse;
import com.sacp.web.util.OffLineUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@DubboService(version = "1.0")
public class ActiveUserService implements SacpWebApi {
    @DubboReference(version = "1.0")
    private MemberApi memberApi;
    @Autowired
    private DefaultWebSessionManager sessionManager;
    @Autowired
    private OffLineUtil offLineUtil;

    @Override
    public List<ActiveUserResponse> getAllActive() {
        SessionDAO sessionDAO = sessionManager.getSessionDAO();
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        List<ActiveUserResponse> responses = new ArrayList<>(activeSessions.size());
        for (Session sesison:activeSessions) {
            ActiveUserResponse response = new ActiveUserResponse();
            Object attribute = sesison.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            String primaryPrincipal = (String) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            MemberRequest request = new MemberRequest();
            request.setNickName(primaryPrincipal);;
            List<MemberResponse> account = memberApi.getAccount(request);
            sesison.stop();
            if (account.size()>0){
                MemberResponse memberResponse = account.get(0);
                response.setSacpId(memberResponse.getSacpId());
                response.setNickName(memberResponse.getNickName());
                response.setHost(sesison.getHost());
                response.setStartTime(sesison.getStartTimestamp());
                response.setLastAccessTime(sesison.getLastAccessTime());
                response.setSessionId(sesison.getId().toString());
                responses.add(response);
            }
        }
        return responses;
    }

    @Override
    public boolean offLineUser(String sessionId) {
        SessionDAO sessionDAO = sessionManager.getSessionDAO();
        Session session = sessionDAO.readSession(sessionId);
        sessionDAO.delete(session);

        Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        String primaryPrincipal = (String) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
        offLineUtil.addOffLineUser(primaryPrincipal);
        return true;
    }
}
