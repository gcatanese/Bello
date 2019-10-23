package com.perosa.bello.core.resource.event.metrics;

import io.prometheus.client.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counters {

    private static final Logger LOGGER = LoggerFactory.getLogger(Counters.class);

    static Counter totalRequestsByHost = Counter.build()
            .name("belloadc_total_requests_by_host")
            .help("Total requests by Host")
            .labelNames("host")
            .register();

    static Counter totalRequestsByChannel = Counter.build()
            .name("belloadc_total_requests_by_channel")
            .help("Total requests by Channel")
            .labelNames("channel")
            .register();

    public void incTotalRequestsByHost(String host) {
        if(host != null) {
            totalRequestsByHost.labels(sanitize(host)).inc();
        }
    }

    public void incTotalRequestsByChannel(String channel) {
        if(channel != null) {
            totalRequestsByChannel.labels(channel).inc();
        }
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
