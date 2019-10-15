package com.perosa.bello.server;

import com.perosa.bello.core.config.Env;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.util.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.networknt.metrics.prometheus.PrometheusGetHandler;

public class MetricsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsHandler.class);

    private static Undertow builder = null;

    public void setUp() {

        final String host = "0.0.0.0";
        final int port = getMetricsHandlerPort();

        if (builder == null) {
            LOGGER.info("starting MetricsHandler on port " + port);

            builder = Undertow.builder()
                    .addHttpListener(port, host)
                    .setHandler(getHttpHandler())
                    .build();

            builder.start();
        }
    }

    private HttpHandler getHttpHandler() {
        return Handlers.routing()
                .add(Methods.GET, "/metrics", new PrometheusGetHandler());

    }

    public int getMetricsHandlerPort() {
        return new Env().getMetricsHandlerPort();
    }
}
