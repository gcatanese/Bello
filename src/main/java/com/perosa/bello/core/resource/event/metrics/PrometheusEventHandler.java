package com.perosa.bello.core.resource.event.metrics;

import com.perosa.bello.core.resource.event.PostEvent;

public class PrometheusEventHandler {

    public void process(PostEvent event) {

        Counters counters = new Counters();
        Summaries summaries = new Summaries();

        counters.incTotalRequestsByHost(event.getTarget());
        counters.incTotalRequestsByChannel(event.getRequest().getChannel());
        summaries.incTotalPayloadSizeByChannel(event.getRequest().getChannel(), event.getRequest().getPayload());
        summaries.incTotalPayloadSizeByHost(event.getTarget(), event.getRequest().getPayload());

    }
}
