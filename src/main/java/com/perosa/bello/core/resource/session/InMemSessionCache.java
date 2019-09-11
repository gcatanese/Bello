package com.perosa.bello.core.resource.session;

import java.util.HashMap;
import java.util.Map;

public class InMemSessionCache implements SessionCache {

    static Map<String, String> map = new HashMap<>();

    @Override
    public String get(String sessionId) {
        return map.get(sessionId);
    }

    @Override
    public void put(String sessionId, String host) {
        if(sessionId != null) {
            map.put(sessionId, host);
        }
    }
}
