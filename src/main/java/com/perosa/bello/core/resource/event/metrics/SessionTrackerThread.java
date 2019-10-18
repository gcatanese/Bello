package com.perosa.bello.core.resource.event.metrics;

import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.resource.session.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.stream.Collectors.groupingBy;

public class SessionTrackerThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionTrackerThread.class);

    private Gauges gauges = new Gauges();

    public void startTracking() {

        Timer timer = new Timer("sessionTrackerThread");
        final long INTERVAL = 1000L * 60 * 1;

        TimerTask task = new TimerTask() {
            public void run() {
                setValues();
            }
        };

        timer.schedule(task, 5000L, INTERVAL);

    }

    private void setValues() {
        Map<String, SessionInfo> map = SessionCache.make().getMap();

        Map<String, List<SessionInfo>> sessionPerChannel = map.values().stream()
                .collect(groupingBy(SessionInfo::getChannel));

        Map<String, List<SessionInfo>> sessionPerHost = map.values().stream()
                .collect(groupingBy(SessionInfo::getHost));

        sessionPerChannel.entrySet().stream()
                .forEach(e -> gauges.setTotalUserSessionsByChannel(e.getKey(), e.getValue().size()));

        sessionPerHost.entrySet().stream()
                .forEach(e -> gauges.setTotalUserSessionsByHost(e.getKey(), e.getValue().size()));

    }


}
