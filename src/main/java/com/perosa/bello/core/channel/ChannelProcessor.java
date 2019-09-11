package com.perosa.bello.core.channel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelProcessor.class);

    public ChannelProcessor() {

    }

    public String extract(String payload) {

        String ret = extractFromDialogFlow(payload);


        return ret;


    }

    String extractFromDialogFlow(String payload) {
        String ret = "";

        String session = findElement("/session", payload);
        String responseId = findElement("/responseId", payload);

        if (!responseId.isEmpty() && !session.isEmpty()) {
            ret = session;
        }


        return ret;
    }

    String findElement(String path, String payload) {
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
