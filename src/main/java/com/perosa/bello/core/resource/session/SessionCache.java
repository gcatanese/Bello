package com.perosa.bello.core.resource.session;

import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.session.provider.InMemSessionCache;
import com.perosa.bello.core.resource.session.provider.RedisSessionCache;
import redis.clients.jedis.Jedis;

public interface SessionCache {

    SessionInfo get(String sessionId);

    void put(String sessionId, SessionInfo sessionInfo);

    int size();

    static SessionCache make() {

        String cacheImpl = new Env().getCacheImpl();
        String redisHost = new Env().getRedisHost();
        int redisPort = new Env().getRedisPort();

        if (cacheImpl.equalsIgnoreCase("redis")) {
            return new RedisSessionCache(new Jedis(redisHost, redisPort));
        } else {
            return new InMemSessionCache();
        }
    }

}