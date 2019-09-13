package com.perosa.bello.core.channel.platform;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MsBotChannelTest {

    @Test
    void extract() {

        String json = "{\n" +
                "  \"type\": \"message\",\n" +
                "  \"text\": \"Hello Microsoft\",\n" +
                "  \"from\": {\n" +
                "    \"id\": \"f104\",\n" +
                "    \"name\": \"perosa\"\n" +
                "  },\n" +
                "  \"locale\": \"en-US\",\n" +
                "  \"textFormat\": \"plain\",\n" +
                "  \"timestamp\": \"2017-10-30T15:42:01.323Z\",\n" +
                "  \"channelData\": {\n" +
                "    \"clientActivityId\": \"1509376542352.5322877619614355.4\"\n" +
                "  },\n" +
                "  \"id\": \"124k29n45ck4k\",\n" +
                "  \"channelId\": \"emulator\",\n" +
                "  \"localTimestamp\": \"2017-10-30T16:42:01+01:00\",\n" +
                "  \"recipient\": {\n" +
                "    \"id\": \"i3i1lh5cdmc9\",\n" +
                "    \"name\": \"TestBot\"\n" +
                "  },\n" +
                "  \"conversation\": {\n" +
                "    \"id\": \"k2mjibh5ch1h\"\n" +
                "  },\n" +
                "  \"serviceUrl\": \"http://localhost:60215\"\n" +
                "}";

        MsBotChannel channel = new MsBotChannel();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload(json);

        assertEquals("f104", channel.extract(request));
    }

}