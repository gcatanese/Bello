package com.perosa.bello.core.channel.platform;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DialogFlowChannelTest {

    @Test
    void extract() {

        String json = "{\n" +
                "    \"responseId\": \"000000001\",\n" +
                "    \"allRequiredParamsPresent\": \"true\",\n" +
                "    \"queryResult\": {\n" +
                "        \"queryText\": \"Hey\"\n" +
                "    },\n" +
                "    \"originalDetectIntentRequest\": {\n" +
                "        \"payload\": \"na\",\n" +
                "        \"messageSource\": \"1\"\n" +
                "    },\n" +
                "    \"session\": \"projects/agent/sessions/001\"\n" +
                "}";

        DialogFlowChannel channel = new DialogFlowChannel();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload(json);

        assertEquals("projects/agent/sessions/001", channel.extract(request));
    }
}