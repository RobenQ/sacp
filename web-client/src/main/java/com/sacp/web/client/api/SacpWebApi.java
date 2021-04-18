package com.sacp.web.client.api;

import com.sacp.web.client.response.ActiveUserResponse;

import java.io.Serializable;
import java.util.List;

public interface SacpWebApi {
    public List<ActiveUserResponse> getAllActive();
    public boolean offLineUser(String sessionId);
}
