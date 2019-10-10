package com.perosa.bello.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public static String findElement(String path, String payload) {
        String ret = null;

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

        LOGGER.trace(path + "-->" + ret);

        return ret;
    }
}
