package com.perosa.bello.core.channel;

import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnknownChannelTest {

    @Test
    void extract() {

        String json = "{\n" +
                "    \"txt\": \"000000001\",\n" +
                "    \"botid\": \"true\",\n" +
                "    \"source\": \"undefined\"\n" +
                "}";


        UnknownChannel channel = new UnknownChannel();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload(json);

        assertNull(channel.extract(request));
    }


}