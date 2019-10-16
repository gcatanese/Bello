package com.perosa.bello.core.resource.event.metrics;

import com.perosa.bello.core.resource.event.Event;

public class PrometheusEventHandler {

    public void process(Event event) {

        new Counters().incTotalRequestsByHost(event.getTarget());
        new Counters().incTotalRequestsByChannel(event.getRequest().getChannel());

    }
}
