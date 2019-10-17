package com.perosa.bello.core.resource.event.metrics;

import com.perosa.bello.core.resource.event.PostEvent;

public class PrometheusEventHandler {

    public void process(PostEvent event) {

        Counters counters = new Counters();

        counters.incTotalRequestsByHost(event.getTarget());
        counters.incTotalRequestsByChannel(event.getRequest().getChannel());
        counters.incTotalPayloadSizeByChannel(event.getRequest().getChannel(), event.getRequest().getPayload());
        counters.incTotalPayloadSizeByHost(event.getTarget(), event.getRequest().getPayload());

    }
}
