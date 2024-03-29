package com.perosa.bello.core.resource.session.provider.redis;

import com.perosa.bello.core.resource.session.SessionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RedisSessionCacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionCacheTest.class);

    @Mock
    private Jedis jedis;

    @Test
    void get() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);
        when(jedis.hget(eq("belloadc:s01"), eq("id"))).thenReturn("01");
        when(jedis.hget(eq("belloadc:s01"), eq("host"))).thenReturn("localhost");

        SessionInfo sessionInfo = redisSessionCache.get("s01");

        assertNotNull(sessionInfo);
        assertEquals("01", sessionInfo.getId());
        assertEquals("localhost", sessionInfo.getHost());

        verify(jedis, times(4)).hget(isA(String.class), isA(String.class));
    }

    @Test
    void getNotFound() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);
        when(jedis.hget(eq("belloadc:s02"), eq("id"))).thenReturn(null);

        SessionInfo sessionInfo = redisSessionCache.get("s02");

        assertNull(sessionInfo);

        verify(jedis, times(1)).hget(isA(String.class), isA(String.class));
    }

    @Test
    void put() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setId("s01");
        sessionInfo.setHost("localhost");
        sessionInfo.setDate(LocalDateTime.now());
        sessionInfo.setChannel("Telegram");

        redisSessionCache.put("s01", sessionInfo);

        verify(jedis, times(4)).hset(isA(String.class), isA(String.class), isA(String.class));
        verify(jedis, times(1)).expire(isA(String.class), isA(Long.class));
    }

    @Test
    void remove() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        redisSessionCache.remove("belloadc:01");

        verify(jedis, times(1)).del(eq("belloadc:01"));
    }

    @Test
    void size() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        int i = redisSessionCache.size();

        verify(jedis, times(1)).keys(isA(String.class));
    }

    @Test
    void writeToString() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        LocalDateTime localDateTime =
                LocalDateTime.of(2018, 7, 7, 12, 34, 57);

        assertEquals("2018-07-07T12:34:57", redisSessionCache.writeToString(localDateTime));
    }

    @Test
    void writeNullDateToString() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        LocalDateTime localDateTime = null;
        assertEquals("", redisSessionCache.writeToString(localDateTime));
    }

    @Test
    void writeToLocalDateTime() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        String str = "2018-07-07T12:34:57";

        LocalDateTime localDateTime = redisSessionCache.writeToLocalDateTime(str);

        assertEquals(2018, localDateTime.getYear());
        assertEquals(7, localDateTime.getMonthValue());
        assertEquals(7, localDateTime.getDayOfMonth());
        assertEquals(12, localDateTime.getHour());
        assertEquals(34, localDateTime.getMinute());
        assertEquals(57, localDateTime.getSecond());
    }

    @Test
    void writeNullToLocalDateTime() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);

        LocalDateTime localDateTime = redisSessionCache.writeToLocalDateTime(null);
        assertNull(localDateTime);
    }

    @Test
    void getMap() {
        RedisSessionCache redisSessionCache = new RedisSessionCache(jedis);
        when(jedis.keys(isA(String.class))).thenReturn(new HashSet<>(Arrays.asList("s1", "s2")));

        assertNotNull(redisSessionCache.getMap());
        assertEquals(2, redisSessionCache.getMap().size());
    }

}