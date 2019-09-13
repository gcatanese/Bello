package com.perosa.bello.core.channel.platform;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.channel.ChannelProcessor;
import com.perosa.bello.core.util.JsonUtil;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsBotChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsBotChannel.class);

    public MsBotChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String session = JsonUtil.findElement("/from/id", request.getPayload());

        if (session != null) {
            ret = session;
        }

        return ret;
    }

}
