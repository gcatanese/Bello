package com.perosa.bello.server;

import com.perosa.bello.core.balancer.Balancer;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DispatchLogicTest {

    @Mock
    HttpServerExchange exchange;
    @Mock
    Balancer balancer;

    @Test
    void dispatch() {
        when(exchange.getRequestScheme()).thenReturn("https");
        when(exchange.getRequestPath()).thenReturn("/webhook");
        when(exchange.getQueryString()).thenReturn("user=perosa");

        new DispatchLogic(balancer).dispatch(exchange);

        verify(exchange, times(1)).dispatch(isA(HttpHandler.class));
        verify(balancer, times(1)).findTarget(isA(InRequest.class));
    }

    @Test
    void buildUrl() {
        when(exchange.getRequestScheme()).thenReturn("https");
        when(exchange.getRequestPath()).thenReturn("/webhook");
        when(exchange.getQueryString()).thenReturn("user=perosa");

        assertEquals("https://localhost/webhook?user=perosa", new DispatchLogic(balancer).buildUrl(exchange, "localhost"));
    }

    @Test
    void buildUrlWithoutQueryString() {
        when(exchange.getRequestScheme()).thenReturn("https");
        when(exchange.getRequestPath()).thenReturn("/webhook");

        assertEquals("https://localhost/webhook", new DispatchLogic(balancer).buildUrl(exchange, "localhost"));
    }

    @Test
    void getRequest() {
        when(exchange.getHostName()).thenReturn("localhost");

        InRequest request = new DispatchLogic(balancer).getRequest(exchange);

        assertNotNull(request);
        assertEquals("localhost", request.getHost());
        assertEquals(null, request.getPayload());
    }

}