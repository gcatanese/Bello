package com.perosa.bello.core.resource.session.provider;

import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.resource.session.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RedisSessionCache implements SessionCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionCache.class);

    private Env env;

    Jedis jedis = null;

    public RedisSessionCache() {
        this.env = new Env();
        this.jedis = new Jedis(getEnv().getRedisHost());
    }

    @Override
    public SessionInfo get(String sessionId) {
        SessionInfo sessionInfo = null;

        String id = getJedis().hget(sessionId, "id");

        if(id != null) {
            sessionInfo.setId(id);
            sessionInfo.setHost(getJedis().hget(sessionId, "host"));
            sessionInfo.setDate(writeToLocalDateTime(getJedis().hget(sessionId, "date")));
            sessionInfo.setAgent(getJedis().hget(sessionId, "agent"));
        }

        return sessionInfo;
    }

    @Override
    public void put(String sessionId, SessionInfo sessionInfo) {
        if (sessionId != null) {
            getJedis().hset(sessionId, "id", sessionInfo.getId());
            getJedis().hset(sessionId, "host", sessionInfo.getHost());
            getJedis().hset(sessionId, "date", writeToString(sessionInfo.getDate()));
            getJedis().hset(sessionId, "agent", sessionInfo.getAgent());
        }
    }

    String writeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    LocalDateTime writeToLocalDateTime(String string) {
        return LocalDateTime.parse(string);
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
