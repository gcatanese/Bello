package com.perosa.bello.core.util;

import com.perosa.bello.core.channel.ChannelProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void findElement() {
        String json = "{\n" +
                "    \"type\": \"message\",\n" +
                "    \"id\": \"bf3cc9a2f5de...\",\n" +
                "    \"timestamp\": \"2016-10-19T20:17:52.2891902Z\",\n" +
                "    \"from\": {\n" +
                "        \"id\": \"1234abcd\",\n" +
                "        \"name\": \"perosa\"\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"id\": \"12345678\",\n" +
                "        \"name\": \"Bello\"\n" +
                "    },\n" +
                "    \"text\": \"Hello Bello\"\n" +
                "}";

        ChannelProcessor channelProcessor = new ChannelProcessor();

        assertEquals("Bello", JsonUtil.findElement("/recipient/name", json));
    }


}