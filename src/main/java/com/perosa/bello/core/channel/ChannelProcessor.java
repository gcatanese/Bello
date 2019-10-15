package com.perosa.bello.core.channel;

import com.perosa.bello.core.channel.platform.*;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelProcessor.class);

    private ChannelInspector channelInspector = new ChannelInspector();

    public ChannelProcessor() {
    }

    public String extract(InRequest request) {
        return getChannel(request).extract(request);
    }

    Channel getChannel(InRequest request) {
        if (getChannelInspector().isDialogFlow(request)) {
            return new DialogFlowChannel();
        } else if (getChannelInspector().isChatfuel(request)) {
            return new ChatfuelChannel();
        } else if (getChannelInspector().isMsBot(request)) {
            return new MsBotChannel();
        } else if (getChannelInspector().isFacebook(request)) {
            return new FacebookChannel();
        } else if (getChannelInspector().isTelegram(request)) {
            return new TelegramChannel();
        } else {
            return new UnknownChannel();
        }
    }


    public ChannelInspector getChannelInspector() {
        return channelInspector;
    }

    public void setChannelInspector(ChannelInspector channelInspector) {
        this.channelInspector = channelInspector;
    }
}
