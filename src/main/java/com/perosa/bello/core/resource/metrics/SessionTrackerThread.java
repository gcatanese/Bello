package com.perosa.bello.core.resource.metrics;

import com.perosa.bello.core.resource.session.SessionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class SessionTrackerThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionTrackerThread.class);

    private Gauges gauges = new Gauges();

    public void startTracking() {

        Timer timer = new Timer("sessionTrackerThread");
        final long INTERVAL = 1000L * 60 * 1;

        TimerTask task = new TimerTask() {
            public void run() {
                setValue();
            }
        };

        timer.schedule(task, 5000L, INTERVAL);

    }

    private void setValue() {
        gauges.setTotalUserSessions(SessionCache.make().size());
    }

}
