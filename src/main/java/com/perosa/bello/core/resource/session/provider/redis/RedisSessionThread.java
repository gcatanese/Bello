package com.perosa.bello.core.resource.session.provider.redis;

import com.perosa.bello.core.resource.session.SessionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class RedisSessionThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionThread.class);


    private RedisSessionCache cache;

    public RedisSessionThread(RedisSessionCache cache) {
        this.cache = cache;
    }

    public void start() {

        Timer timer = new Timer("rediSessionThread");
        final long INTERVAL = 1000L * 60 * SessionCache.INTERVAL_CHECK_IN_MIN;

        TimerTask task = new TimerTask() {
            public void run() {
                cleanUp();
            }
        };

        timer.schedule(task, 5000L, INTERVAL);

    }

    void cleanUp() {
        cache.getMap().entrySet().stream()
                .filter(e -> e.getValue().getDate().isBefore(LocalDateTime.now().minusMinutes(SessionCache.MAX_DURATION_IN_MIN)))
                .forEach(e -> cache.remove(e.getKey()));
    }
}
