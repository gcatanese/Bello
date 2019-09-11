package com.perosa.bello.server;

import com.perosa.bello.core.Balancer;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.RedirectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispatchLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchLogic.class);

    private Balancer balancer;

    public DispatchLogic() {
        this.balancer = Balancer.getInstance();
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

        return request;
    }

    String extractBody(HttpServerExchange exchange) {
        StringBuilder requestBody = new StringBuilder();

        if (exchange.getRequestReceiver() != null) {
            exchange.getRequestReceiver().receiveFullString((ex, data) -> {
                requestBody.append(data);
            });
        }

        return requestBody.toString();
    }

    public Balancer getBalancer() {
        return balancer;
    }

    public void setBalancer(Balancer balancer) {
        this.balancer = balancer;
    }
}
