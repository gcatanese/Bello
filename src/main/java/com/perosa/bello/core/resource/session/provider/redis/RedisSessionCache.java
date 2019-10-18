package com.perosa.bello.core.resource.session.provider.redis;

import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.resource.session.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RedisSessionCache implements SessionCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionCache.class);

    private static final String REDIS_KEY_NAMESPACE = "belloadc";

    private Env env;

    private Jedis jedis;

    public RedisSessionCache(Jedis jedis) {
        this.env = new Env();
        this.jedis = jedis;

        new RedisSessionThread(this).start();
    }

    @Override
    public SessionInfo get(String sessionId) {

        String key = REDIS_KEY_NAMESPACE + ":" + sessionId;

        return fetchFromRedis(key);
    }

    @Override
    public void put(String sessionId, SessionInfo sessionInfo) {

        if (sessionId != null) {
            String key = REDIS_KEY_NAMESPACE + ":" + sessionId;

            sendToRedis(key, sessionInfo);
        }
    }

    @Override
    public void remove(String key) {
        deleteFromRedis(key);
    }

    @Override
    public int size() {
        return getMap().size();
    }

    @Override
    public Map<String, SessionInfo> getMap() {
        Map<String, SessionInfo> map = new HashMap<>();

        getKeysFromRedis().stream().forEach(e -> map.put(e, fetchFromRedis(e)));

        LOGGER.trace("Redis-->" + map);

        return map;
    }

///// Redis interactions

    private SessionInfo fetchFromRedis(String key) {
        SessionInfo sessionInfo = null;

        String id = getJedis().hget(key, "id");

        if (id != null) {
            sessionInfo = new SessionInfo();

            sessionInfo.setId(id);
            sessionInfo.setHost(getJedis().hget(key, "host"));
            sessionInfo.setDate(writeToLocalDateTime(getJedis().hget(key, "date")));
            sessionInfo.setChannel(getJedis().hget(key, "channel"));
        }

        return sessionInfo;
    }

    private void sendToRedis(String key, SessionInfo sessionInfo) {

        getJedis().hset(key, "id", sessionInfo.getId());
        getJedis().hset(key, "host", sessionInfo.getHost());
        getJedis().hset(key, "date", writeToString(sessionInfo.getDate()));
        getJedis().hset(key, "channel", sessionInfo.getChannel());

        getJedis().expire(key, 60 * 30);
    }

    private void deleteFromRedis(String key) {

        long ret = getJedis().del(key);

        if (ret != 1) {
            LOGGER.warn("Error while DEL from Redis key:" + key + " ret:" + ret);
        }
    }

    private Set<String> getKeysFromRedis() {
        return getJedis().keys(REDIS_KEY_NAMESPACE + "*");
    }

///// Helpers

    String writeToString(LocalDateTime localDateTime) {
        return (localDateTime != null ? localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "");
    }

    LocalDateTime writeToLocalDateTime(String string) {
        return (string != null ? LocalDateTime.parse(string) : null);
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
