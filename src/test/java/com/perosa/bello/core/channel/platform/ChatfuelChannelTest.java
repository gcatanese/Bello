package com.perosa.bello.core.channel.platform;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

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

}