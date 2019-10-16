package com.perosa.bello.core.resource.event.metrics;

import com.perosa.bello.core.resource.event.Event;

public class PrometheusEventHandler {

    public void process(Event event) {

        Counters counters = new Counters();

        counters.incTotalRequestsByHost(event.getTarget());
        counters.incTotalRequestsByChannel(event.getRequest().getChannel());
        counters.incTotalPayloadSizeByChannel(event.getRequest().getChannel(), event.getRequest().getPayload());

    }
}
