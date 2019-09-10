package com.perosa.bello.core.resource.channel;

public class ChannelFactory {

    public static Channel make(String payload) {
        return new ChannelProcessor(payload);
    }


}
