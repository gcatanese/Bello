package com.perosa.bello.core.resource.metrics;

import io.prometheus.client.Gauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gauges {

    private static final Logger LOGGER = LoggerFactory.getLogger(Gauges.class);

    static final Gauge totalUserSessions = Gauge.build()
            .name("belloadc_total_user_sessions")
            .help("Number of User Sessions in cache")
            .register();

    public void setTotalUserSessions(int val) {
        totalUserSessions.set(val);
    }
}
