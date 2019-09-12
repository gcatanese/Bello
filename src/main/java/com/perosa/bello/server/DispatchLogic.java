package com.perosa.bello.server;

import com.perosa.bello.core.balancer.Balancer;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.RedirectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DispatchLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchLogic.class);

    private Balancer balancer;

    public DispatchLogic(Balancer balancer) {
        this.balancer = balancer;
    }

    void dispatch(HttpServerExchange exchange) {

        InRequest request = getRequest(exchange);

        String target = getBalancer().findTarget(request);

        String url = buildUrl(exchange, target);

        LOGGER.info("sendTo " + url);

        exchange.dispatch(new RedirectHandler(url));
    }

    String buildUrl(HttpServerExchange exchange, String target) {
        return exchange.getRequestScheme() + "://" +
                target +
                exchange.getRequestPath() +
                (exchange.getQueryString() == null ? "" : "?" + exchange.getQueryString());
    }

    InRequest getRequest(HttpServerExchange exchange) {
        InRequest request = new InRequest();
        request.setHost(exchange.getHostName());
        request.setPayload(extractBody(exchange));
        request.setHeaders(extractHeaders(exchange));

        return request;
    }

    String extractBody(HttpServerExchange exchange) {
        String body = null;

        StringBuilder requestBody = new StringBuilder();

        if (exchange.getRequestReceiver() != null) {
            exchange.getRequestReceiver().receiveFullString((ex, data) -> {
                requestBody.append(data);
            });
        }

        if(requestBody.length() > 0) {
            body = requestBody.toString();
        }

        return body;
    }

    Map<String, String> extractHeaders(HttpServerExchange exchange) {
        Map<String, String> headers = new HashMap<>();

        exchange.getRequestHeaders().getHeaderNames().stream()
                .forEach(m -> headers.put(m.toString(),
                        exchange.getRequestHeaders().get(m).getFirst()
                ));

        return headers;
    }

    public Balancer getBalancer() {
        return balancer;
    }

    public void setBalancer(Balancer balancer) {
        this.balancer = balancer;
    }
}
