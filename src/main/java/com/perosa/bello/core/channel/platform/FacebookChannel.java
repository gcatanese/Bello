package com.perosa.bello.core.channel.platform;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.channel.ChannelProcessor;
import com.perosa.bello.core.util.JsonUtil;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacebookChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookChannel.class);

    public FacebookChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String id = JsonUtil.findElement("/entry/0/messaging/0/sender/id", request.getPayload());

        if (id != null) {
            ret = id;
        }

        return ret;
    }

}
