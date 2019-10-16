package com.perosa.bello.core.resource.event.metrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
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

    static final Summary totalPayloadSizeByHost = Summary.build()
            .name("belloadc_total_payload_size_by_host")
            .help("Total Payload Size by Host")
            .labelNames("host")
            .register();

    static final Summary totalPayloadSizeByChannel = Summary.build()
            .name("belloadc_total_payload_size_by_channel")
            .help("Total Payload Size by Channel")
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

    public void incTotalPayloadSizeByChannel(String channel, String payload) {
        if(channel != null && payload != null) {
            totalPayloadSizeByChannel.labels(channel).observe(payload.length());
        }
    }

    public void incTotalPayloadSizeByHost(String host, String payload) {
        if(host != null && payload != null) {
            totalPayloadSizeByChannel.labels(sanitize(host)).observe(payload.length());
        }
    }

    String sanitize(String host) {
        host = host.replace(".", "_");

        return host;
    }
}
