package com.perosa.bello.core.channel;

import com.perosa.bello.core.channel.platform.*;
import com.perosa.bello.server.InRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ChannelProcessorTest {

    @Test
    void getUnknownChannel() {

        Map<String, String> headers = new HashMap<>();

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelProcessor().getChannel(request) instanceof UnknownChannel);
    }
    @Test
    void getDialogFlowChannel() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "Google-Dialogflow");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelProcessor().getChannel(request) instanceof DialogFlowChannel);
    }

    @Test
    void getChatfuelChannel() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "Chatfuel");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelProcessor().getChannel(request) instanceof ChatfuelChannel);
    }

    @Test
    void getMsBotChannel() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "microsoft-botFramework");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelProcessor().getChannel(request) instanceof MsBotChannel);
    }

    @Test
    void getFacebookChannel() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "facebook");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelProcessor().getChannel(request) instanceof FacebookChannel);
    }

    @Test
    void getTelegramChannel() {

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "telegram");

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setHeaders(headers);

        assertTrue(new ChannelProcessor().getChannel(request) instanceof TelegramChannel);
    }

}