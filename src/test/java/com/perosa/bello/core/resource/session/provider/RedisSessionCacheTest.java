package com.perosa.bello.core.resource.session.provider;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RedisSessionCacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionCacheTest.class);

    @Test
    void writeToString() {
        RedisSessionCache redisSessionCache = new RedisSessionCache();

        LocalDateTime localDateTime =
                LocalDateTime.of(2018, 7, 7, 12, 34, 57);

        assertEquals("2018-07-07T12:34:57", redisSessionCache.writeToString(localDateTime));

    }

    @Test
    void writeToLocalDateTime() {
        RedisSessionCache redisSessionCache = new RedisSessionCache();

        String str = "2018-07-07T12:34:57";

        LocalDateTime localDateTime = redisSessionCache.writeToLocalDateTime(str);

        assertEquals(2018, localDateTime.getYear());
        assertEquals(7, localDateTime.getMonthValue());
        assertEquals(7, localDateTime.getDayOfMonth());
        assertEquals(12, localDateTime.getHour());
        assertEquals(34, localDateTime.getMinute());
        assertEquals(57, localDateTime.getSecond());

    }
}