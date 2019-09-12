package com.perosa.bello.core.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtilTest.class);
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

        assertEquals("Bello", JsonUtil.findElement("/recipient/name", json));
    }

    @Test
    void findElementInArray() {
        String json = "{\"object\":\"page\"," +
                "\"entry\":[{\"id\":\"323526848385736\",\"time\":1534466505487,\n" +
                " \"messaging\":[{\"sender\":{\"id\":\"2398612963492357\"},\n" +
                " \"recipient\":{\"id\":\"323526848385736\"}," +
                "\"timestamp\":1534466505041,\n" +
                " \"message\":{\"mid\":\"64O__IG\", \"seq\":106420,\"text\":\"Hello World\"}}]}]" +
                "}";

        assertEquals("2398612963492357", JsonUtil.findElement("/entry/0/messaging/0/sender/id", json));
    }

}