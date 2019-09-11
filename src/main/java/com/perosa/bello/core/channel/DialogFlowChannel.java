package com.perosa.bello.core.channel;

import com.perosa.bello.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DialogFlowChannel implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialogFlowChannel.class);

    public DialogFlowChannel() {
    }

    public String extract(String payload) {

        String ret = null;

        String session = JsonUtil.findElement("/session", payload);
        String responseId = JsonUtil.findElement("/responseId", payload);

        if (!responseId.isEmpty() && !session.isEmpty()) {
            ret = session;
        }


        return ret;
    }

}
