package com.perosa.bello.core.resource.channel;

public class ChannelFactory {

    public static Channel make() {
        return new ChannelProcessor();
    }


}
