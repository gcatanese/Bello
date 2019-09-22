package com.perosa.bello.core.channel.platform;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatfuelChannelTest {

    @Test
    void extract() {

        String json = "{\n" +
                " \"messenger user id\": \"1545521221\",\n" +
                " \"last name\": \"perosa\",\n" +
                " \"last visited block name\": \"default\",\n" +
                " \"last visited block id\": \"Block 1\",\n" +
                " \"last clicked button name\": \"Sign Up\"\n" +
                "}";

        ChatfuelChannel channel = new ChatfuelChannel();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload(json);

        assertEquals("1545521221", channel.extract(request));
    }

    @Test
    void extractFromParameters() {

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put("messenger user id", new String[]{"1545521221"});
        parameters.put("first name", new String[]{"John"});
        parameters.put("last name", new String[]{"Wiggle"});

        ChatfuelChannel channel = new ChatfuelChannel();

        assertEquals("1545521221", channel.extractFromParameters(parameters));

    }

}