package com.perosa.bello.server;

import com.perosa.bello.core.Balancer;
import com.perosa.bello.core.resource.channel.Channel;
import com.perosa.bello.core.resource.channel.ChannelFactory;
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

                                try {
                                    doHandleRequest(exchange);

                                } catch (Exception e) {
                                    LOGGER.error(e.getMessage(), e);
                                    exchange.setStatusCode(404);
                                }

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

        InRequest request = new InRequest();
        request.setHost(exchange.getHostName());
        request.setPayload(extractBody(exchange));

        String target = Balancer.make().findTarget(request);

        String url =
                exchange.getRequestScheme() + "://" +
                        target +
                        exchange.getRequestPath() +
                        (exchange.getQueryString() == null ? "" : "?" + exchange.getQueryString());

        LOGGER.info("sendTo " + url);

        exchange.dispatch(new RedirectHandler(url));
    }

    String extractBody(HttpServerExchange exchange) {
        StringBuilder requestBody = new StringBuilder();

        exchange.getRequestReceiver().receiveFullString((ex, data) -> {
            requestBody.append(data);
        });

        return requestBody.toString();
    }

    public int getPort() {
        return 8888;
    }
}
