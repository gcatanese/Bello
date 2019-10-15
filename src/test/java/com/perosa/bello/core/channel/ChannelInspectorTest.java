package com.perosa.bello.core.channel;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChannelInspectorTest {

    @Test
    void getChannel() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "telegram");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertEquals("telegram", new ChannelInspector().getChannel(request));
    }

    @Test
    void getUndefinedChannel() {

        Map<String, String> headers = new HashMap<>();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertEquals("n/a", new ChannelInspector().getChannel(request));
    }


    @Test
    void isTelegram() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "TELEGRAM");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelInspector().isTelegram(request));
    }
}