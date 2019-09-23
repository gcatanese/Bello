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

public class ChatfuelChannel extends ChannelProcessor implements Channel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatfuelChannel.class);

    private static final String ID = "messenger user id";

    public ChatfuelChannel() {
    }

    public String extract(InRequest request) {

        String ret = null;

        String id = extractFromParameters(request.getParameters());

        if (id == null) {
            ret = extractFromBody(request.getPayload());
            if (id == null) {
                ret = extractFromEncodedUrlInBody(request.getPayload());
            }
        }

        return ret;
    }

    String extractFromParameters(Map<String, String[]> parameters) {
        String ret = null;

        if (parameters != null && parameters.get(ID) != null) {
            ret = parameters.get(ID)[0];
        }

        return ret;
    }

    String extractFromBody(String body) {
        String ret = null;

        if (body != null) {
            String id = JsonUtil.findElement("/" + ID, body);

            if (!id.isEmpty()) {
                ret = id;
            }
        }

        return ret;
    }

    String extractFromEncodedUrlInBody(String body) {
        String ret = null;

        if (body != null) {
            body = body.replace("%20", " ");

            String[] elements = body.split("&");

            Optional<String> element = Arrays.stream(elements)
                    .filter(e -> e.startsWith(ID))
                    .findFirst();

            if (element.isPresent()) {
                String paramName = ID + "=";
                ret = element.get().substring(paramName.length()).trim();
            }
        }

        return ret;
    }

}
