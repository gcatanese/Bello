package com.perosa.bello.core.resource.event;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);

    private static MBassador bus;

    static {
        start();
    }

    private static void start() {

        bus = new MBassador(new BusConfiguration()
                .addFeature(Feature.SyncPubSub.Default())
                .addFeature(Feature.AsynchronousHandlerInvocation.Default())
                .addFeature(Feature.AsynchronousMessageDispatch.Default())
                .addPublicationErrorHandler(new IPublicationErrorHandler.ConsoleLogger())
                .setProperty(IBusConfiguration.Properties.BusId, "global bus"));

        bus.subscribe(new EventListener());

    }

    public static void sendEvent(Event event) {
        bus.post(event).asynchronously();
    }

}
