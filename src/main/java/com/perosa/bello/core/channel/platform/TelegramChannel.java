package com.perosa.bello.core.channel.platform;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.channel.ChannelProcessor;
import com.perosa.bello.core.util.JsonUtil;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelegramChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramChannel.class);

    public TelegramChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String id = JsonUtil.findElement("/0/message/0/from/0/id", request.getPayload());

        if (id != null) {
            ret = id;
        }

        return ret;
    }

}
