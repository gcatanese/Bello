package com.perosa.bello.core.resource.event.metrics;

import io.prometheus.client.Gauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gauges {

    private static final Logger LOGGER = LoggerFactory.getLogger(Gauges.class);

    static final Gauge totalUserSessionsByHost = Gauge.build()
            .name("belloadc_total_user_sessions_by_host")
            .help("Number of User Sessions by Host")
            .labelNames("host")
            .register();

    static final Gauge totalUserSessionsByChannel = Gauge.build()
            .name("belloadc_total_user_sessions_by_channel")
            .help("Number of User Sessions by Channel")
            .labelNames("channel")
            .register();

    void setTotalUserSessionsByHost(String host, int val) {
        totalUserSessionsByHost.labels(sanitize(host)).set(val);
    }

    void setTotalUserSessionsByChannel(String channel, int val) {
        totalUserSessionsByChannel.labels(channel).set(val);
    }

    String sanitize(String host) {
        if(host.lastIndexOf(".") > -1) {
            host = host.substring(0, host.lastIndexOf("."));
        }

        if(host.lastIndexOf(".") > -1) {
            host = host.substring(0, host.lastIndexOf("."));
        }

        host = host.replace(".", "_");

        return host;
    }

}
