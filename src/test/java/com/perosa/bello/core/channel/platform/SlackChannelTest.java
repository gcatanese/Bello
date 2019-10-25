package com.perosa.bello.core.channel.platform;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlackChannelTest {

    @Test
    void extract() {

        String json = "{\n" +
                "    \"token\": \"#######\",\n" +
                "    \"team_id\": \"dev\",\n" +
                "    \"api_app_id\": \"app\",\n" +
                "    \"event\": {\n" +
                "        \"client_msg_id\": \"90629f24-1d50-4495-b83c-4184df637aa4\",\n" +
                "        \"type\": \"message\",\n" +
                "        \"text\": \"Ciao Perosa\",\n" +
                "        \"user\": \"S8J9K0L87H\",\n" +
                "        \"ts\": \"1571995960.001000\",\n" +
                "        \"team\": \"dev\",\n" +
                "        \"channel\": \"1\",\n" +
                "        \"event_ts\": \"1571995960.001000\",\n" +
                "        \"channel_type\": \"channel\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvPSV72RG8\",\n" +
                "    \"event_time\": 1571995960,\n" +
                "    \"authed_users\": [\"######\"]\n" +
                "}";

        SlackChannel channel = new SlackChannel();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload(json);

        assertEquals("S8J9K0L87H", channel.extract(request));
    }
}