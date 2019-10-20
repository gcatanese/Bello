package com.perosa.bello.core.resource.event.metrics;

import com.perosa.bello.core.resource.event.PostEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrometheusEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrometheusEventHandler.class);

    public void process(PostEvent event) {

        LOGGER.debug(event.toString());

        Counters counters = new Counters();
        Summaries summaries = new Summaries();

        counters.incTotalRequestsByHost(event.getTarget());
        counters.incTotalRequestsByChannel(event.getRequest().getChannel());
        summaries.incTotalPayloadSizeByChannel(event.getRequest().getChannel(), event.getRequest().getPayload());
        summaries.incTotalPayloadSizeByHost(event.getTarget(), event.getRequest().getPayload());

    }
}
