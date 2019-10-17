package com.perosa.bello.core.resource.event.metrics;

import io.prometheus.client.Summary;

public class Summaries {

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
