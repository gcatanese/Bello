package com.perosa.bello.core.resource.event;

import com.perosa.bello.core.resource.event.metrics.PrometheusEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);

    public static void sendEvent(Event event) {
        new PrometheusEventHandler().process((PostEvent) event);
    }

}
