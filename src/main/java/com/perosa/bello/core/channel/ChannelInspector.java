package com.perosa.bello.core.channel;

import com.perosa.bello.server.InRequest;

public class ChannelInspector {

    public boolean isDialogFlow(InRequest request) {
        return request.getHeaders().containsValue("Google-Dialogflow");
    }

    public boolean isChatfuel(InRequest request) {
        return request.getHeaders().containsValue("Chatfuel");
    }

    public boolean isMsBot(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().trim().contains("microsoft-botframework");
    }

    public boolean isFacebook(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().trim().contains("facebook");
    }

    public boolean isTelegram(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().trim().contains("telegram");
    }

    public String getChannel(InRequest request) {
        String header = request.getHeaders().get("user-agent");

        return (header != null ? header.toLowerCase().trim() : "n/a");
    }
}
