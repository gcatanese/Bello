package com.perosa.bello.core.channel.platform;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.channel.ChannelProcessor;
import com.perosa.bello.core.util.JsonUtil;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class GenericChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericChannel.class);

    private static final String PATH = "/id";

    public GenericChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String session = JsonUtil.findElement(PATH, request.getPayload());

        if (session != null) {
            ret = session;
        }

        return ret;
    }

}
