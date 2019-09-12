package com.perosa.bello.core.channel.platform;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.channel.ChannelProcessor;
import com.perosa.bello.core.util.JsonUtil;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatfuelChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatfuelChannel.class);

    public ChatfuelChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String id = JsonUtil.findElement("/messenger user id", request.getPayload());

        if (!id.isEmpty()) {
            ret = id;
        }

        return ret;
    }

}
