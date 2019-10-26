package com.perosa.bello.core.resource.session.provider.local;

import com.perosa.bello.core.resource.session.SessionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InMemSessionThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemSessionThread.class);

    private InMemSessionCache cache;

    public InMemSessionThread(InMemSessionCache cache) {
        this.cache = cache;
    }

    public void start() {

        Timer timer = new Timer("inMemSessionThread");
        final long INTERVAL = 1000L * 60 * SessionCache.INTERVAL_CHECK_IN_MIN;

        TimerTask task = new TimerTask() {
            public void run() {
                cleanUp();
            }
        };

        timer.schedule(task, 5000L, INTERVAL);

    }

    void cleanUp() {
        List<String> expired = new ArrayList<>();

        cache.getMap().entrySet().stream()
                .filter(e -> e.getValue().getDate().isBefore(LocalDateTime.now().minusMinutes(SessionCache.MAX_DURATION_IN_MIN)))
                .forEach(e -> expired.add(e.getKey()));

        if(!expired.isEmpty()) {
            LOGGER.trace("Discarding " + expired);
            cache.getMap().keySet().removeAll(expired);
        }
    }
}
