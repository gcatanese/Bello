package com.perosa.bello.core.channel;

import com.perosa.bello.core.channel.platform.ChatfuelChannel;
import com.perosa.bello.core.channel.platform.DialogFlowChannel;
import com.perosa.bello.core.channel.platform.UnknownChannel;
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
        if (request.getHeaders().containsValue("Google-Dialogflow")) {
            return (DialogFlowChannel)this;
        } else if (request.getHeaders().containsValue("Chatfuel")) {
            return (ChatfuelChannel)this;
        } else {
            return (UnknownChannel)this;
        }
    }

}
