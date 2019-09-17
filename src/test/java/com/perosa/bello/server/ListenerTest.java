package com.perosa.bello.server;

import com.networknt.client.rest.LightRestClient;
import com.perosa.bello.core.balancer.Balancer;
import io.undertow.client.ClientResponse;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListenerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerTest.class);

    Listener listener;

    @Mock
    private DispatchLogic dispatchLogic;

    @BeforeEach
    private void init() {
        dispatchLogic = Mockito.mock(DispatchLogic.class);
        listener = new Listener(dispatchLogic);

        listener.setUp();
    }

    @Test
    void test() throws Exception {
        LightRestClient lightRestClient = new LightRestClient();

        ClientResponse clientResponse = lightRestClient.get("http://localhost:8888", "/belloadc/test", ClientResponse.class, new HashMap<String, String>());

        assertEquals(200, clientResponse.getResponseCode());
    }

    @Test
    void get() throws Exception {

        LightRestClient lightRestClient = new LightRestClient();

        ClientResponse clientResponse = lightRestClient.get("http://localhost:8888", "/svc1", ClientResponse.class, new HashMap<String, String>());

        assertEquals(200, clientResponse.getResponseCode());
    }

    @Test
    void getPort() {
        assertEquals(8888, listener.getPort());
    }
}