package com.perosa.bello.core.resource.session;

import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.session.provider.InMemSessionCache;
import com.perosa.bello.core.resource.session.provider.RedisSessionCache;
import redis.clients.jedis.Jedis;

public interface SessionCache {

    SessionInfo get(String sessionId);

    void put(String sessionId, SessionInfo sessionInfo);

    static SessionCache make() {

        if(new Env().getCacheImpl().equalsIgnoreCase("redis")) {
            return new RedisSessionCache(new Jedis(new Env().getRedisHost()));
        } else {
            return new InMemSessionCache();
        }
    }

}