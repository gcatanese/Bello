package com.perosa.bello.core.resource.session.provider.local;

import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.resource.session.SessionInfo;

import java.util.HashMap;
import java.util.Map;

public class InMemSessionCache implements SessionCache {

    static Map<String, SessionInfo> map = new HashMap<>();

    public InMemSessionCache() {
        new InMemSessionThread(this).start();
    }

    @Override
    public SessionInfo get(String sessionId) {
        return map.get(sessionId);
    }

    @Override
    public void put(String sessionId, SessionInfo sessionInfo) {
        if(sessionId != null) {
            map.put(sessionId, sessionInfo);
        }
    }

    @Override
    public void remove(String sessionId) {
        if(sessionId != null) {
            map.remove(sessionId);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Map<String, SessionInfo> getMap() {
        return map;
    }
}
