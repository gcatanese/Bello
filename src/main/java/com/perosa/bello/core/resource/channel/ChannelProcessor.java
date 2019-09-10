package com.perosa.bello.core.resource.channel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelProcessor.class);

    String payload = null;
    String sessionId = null;

    public ChannelProcessor(String payload) {
        this.payload = payload;

        process();
    }

    public String extractSessionId() {
        return sessionId;
    }

    void process() {


        if (tryDialogFlow()) {
            return;
        }

        if (tryMsBot()) {
            return;
        }

        if (tryChatfuel()) {
            return;
        }


    }

    boolean tryDialogFlow() {
        boolean ret = false;

        String session = findElement("/session");
        String responseId = findElement("/responseId");

        if (!responseId.isEmpty() && !session.isEmpty()) {
            this.sessionId = session;
            ret = true;
        }


        return ret;
    }

    boolean tryMsBot() {
        boolean ret = false;

        return ret;
    }

    boolean tryChatfuel() {
        boolean ret = false;

        return ret;
    }

    String findElement(String path) {
        String ret = "";

        if (payload != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(payload);

                if (jsonNode != null) {
                    jsonNode = jsonNode.at(path);

                    if (jsonNode != null) {
                        ret = jsonNode.asText();
                    }
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        LOGGER.debug(path + "-->" + ret);

        return ret;
    }
}
