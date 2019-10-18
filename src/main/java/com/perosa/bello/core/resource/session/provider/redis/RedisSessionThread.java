package com.perosa.bello.core.resource.session.provider.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class RedisSessionThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionThread.class);

    private static final long MAX_DURATION = 15;
    private RedisSessionCache cache;

    public RedisSessionThread(RedisSessionCache cache) {
        this.cache = cache;
    }

    public void start() {

        Timer timer = new Timer("rediSessionThread");
        final long INTERVAL = 1000L * 60 * 5;

        TimerTask task = new TimerTask() {
            public void run() {
                cleanUp();
            }
        };

        timer.schedule(task, 5000L, INTERVAL);

    }

    void cleanUp() {
        cache.getMap().entrySet().stream()
                .filter(e -> e.getValue().getDate().isBefore(LocalDateTime.now().minusMinutes(MAX_DURATION)))
                .forEach(e -> cache.remove(e.getKey()));
    }
}
