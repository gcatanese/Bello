package com.perosa.bello.core.resource.session.provider;

import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.resource.session.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisSessionCache implements SessionCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionCache.class);

    static Map<String, SessionInfo> map = new HashMap<>();
    Jedis jedis = null;

    public RedisSessionCache() {
        this.jedis = new Jedis("localhost");

        LOGGER.info(jedis.toString());
    }

    @Override
    public SessionInfo get(String sessionId) {
        return map.get(sessionId);
    }

    @Override
    public void put(String sessionId, SessionInfo sessionInfo) {
        if(sessionId != null) {
            jedis.set(sessionId, sessionInfo.getHost());
        }
    }

    Map<String, SessionInfo> getMap() {
        return map;
    }
}
