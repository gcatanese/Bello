package com.perosa.bello.core.channel.platform;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelegramChannelTest {

    @Test
    void extract() {

        String json = "[{\n" +
                "\"update_id\": \"99999\",\n" +
                "\"message\": [{\n" +
                "\"message_id\": \"88\",\n" +
                "\"from\": [{\n" +
                "\"id\": \"123456\",\n" +
                "\"first_name\": \"John\",\n" +
                "\"last_name\": \"Wiggle\",\n" +
                "\"username\": \"john\",\n" +
                "\"language_code\": \"en-UK\"\n" +
                "}],\n" +
                "\"date\": \"1568246400\",\n" +
                "\"text\": \"Hello Telegram\"\n" +
                "}]\n" +
                "}]";


        TelegramChannel channel = new TelegramChannel();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload(json);

        assertEquals("123456", channel.extract(request));
    }

}