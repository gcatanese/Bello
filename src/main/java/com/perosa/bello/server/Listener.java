package com.perosa.bello.server;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.RedirectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    private static Undertow builder = null;

    public void setUp() {

        final String host = "0.0.0.0";
        final int port = getPort();

        try {

            if (builder == null) {
                LOGGER.info("listening on port " + port);

                builder = Undertow.builder()
                        .addHttpListener(port, host)
                        .setHandler(new HttpHandler() {
                            @Override
                            public void handleRequest(HttpServerExchange exchange) throws Exception {

                                LOGGER.info("exchange " + exchange);
                                doHandleRequest(exchange);

                            }
                        })
                        .build();

                builder.start();

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }


    }

    private void doHandleRequest(HttpServerExchange exchange) {

        dispatch(exchange);

    }

    private void dispatch(HttpServerExchange exchange) {
        String url = exchange.getRequestURL() +
                (exchange.getQueryString() == null ? "" : "?" + exchange.getQueryString());

        exchange.dispatch(new RedirectHandler(url));
    }

    public int getPort() {
        return 8888;
    }
}
