package com.perosa.bello.core.resource.session;

import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.session.provider.local.InMemSessionCache;
import com.perosa.bello.core.resource.session.provider.redis.RedisSessionCache;
import redis.clients.jedis.Jedis;

import java.util.Map;

public interface SessionCache {

    long MAX_DURATION_IN_MIN = 2;
    long INTERVAL_CHECK_IN_MIN = 1;

    SessionInfo get(String sessionId);

    void put(String sessionId, SessionInfo sessionInfo);

    void remove(String sessionId);

    Map<String, SessionInfo> getMap();

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