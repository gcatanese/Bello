package com.perosa.bello.core.resource.event;

import com.perosa.bello.core.resource.event.metrics.PrometheusEventHandler;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import net.engio.mbassy.listener.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

    @Handler(delivery = Invoke.Asynchronously)
    public void handle(Event event){
        LOGGER.debug("handling " + event);

        new PrometheusEventHandler().process(event);

    }
}
