package com.perosa.bello.core.channel;

import com.perosa.bello.core.channel.platform.*;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelProcessor.class);

    public ChannelProcessor() {
    }

    public String extract(InRequest request) {
        return getChannel(request).extract(request);
    }

    Channel getChannel(InRequest request) {
        if (isDialogFlow(request)) {
            return new DialogFlowChannel();
        } else if (isChatfuel(request)) {
            return new ChatfuelChannel();
        } else if (isMsBot(request)) {
            return new MsBotChannel();
        } else if (isFacebook(request)) {
            return new FacebookChannel();
        } else if (isTelegram(request)) {
            return new TelegramChannel();
        } else if (isSlack(request)) {
            return new SlackChannel();
        } else {
            return new UnknownChannel();
        }
    }

    boolean isDialogFlow(InRequest request) {
        return request.getHeaders().containsValue("Google-Dialogflow");
    }

    boolean isChatfuel(InRequest request) {
        return request.getHeaders().containsValue("Chatfuel");
    }

    boolean isMsBot(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().contains("microsoft-botframework");
    }

    boolean isFacebook(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().contains("facebook");
    }

    boolean isTelegram(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().contains("telegram");
    }

    boolean isSlack(InRequest request) {
        String header = request.getHeaders().get("user-agent");
        return header != null && header.toLowerCase().contains("slackbot");
    }

}
