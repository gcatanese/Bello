package com.perosa.bello.core.resource;

public interface SessionCache {

    String get(String sessionId);

    void put(String sessionId, String host);

    static SessionCache make() {
        return new InMemSessionCache();
    }

}