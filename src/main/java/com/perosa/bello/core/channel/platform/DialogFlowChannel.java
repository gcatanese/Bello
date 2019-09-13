package com.perosa.bello.core.channel.platform;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.channel.ChannelProcessor;
import com.perosa.bello.core.util.JsonUtil;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DialogFlowChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialogFlowChannel.class);

    public DialogFlowChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String session = JsonUtil.findElement("/session", request.getPayload());

        if (session != null) {
            ret = session;
        }

        return ret;
    }

}
