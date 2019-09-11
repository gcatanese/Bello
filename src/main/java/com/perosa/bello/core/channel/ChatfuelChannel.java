package com.perosa.bello.core.channel;

import com.perosa.bello.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatfuelChannel implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatfuelChannel.class);

    public ChatfuelChannel() {
    }

    public String extract(String payload) {

        String ret = null;

        String id = JsonUtil.findElement("/messenger user id", payload);

        if (!id.isEmpty()) {
            ret = id;
        }

        return ret;
    }

}
