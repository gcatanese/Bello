package com.perosa.bello.core.resource.metrics;

import io.prometheus.client.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counters {

    private static final Logger LOGGER = LoggerFactory.getLogger(Counters.class);

    static Counter totalRequests =  Counter.build()
            .name("belloadc_total_requests")
            .help("Total requests")
            .labelNames("host")
            .register();

    public void incTotalRequests(String host) {
        totalRequests.labels(sanitize(host)).inc();
    }

    String sanitize(String host) {
        host = host.replace(".", "_");

        return host;
    }
}
