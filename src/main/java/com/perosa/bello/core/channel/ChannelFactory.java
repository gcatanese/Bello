package com.perosa.bello.core.channel;

public class ChannelFactory {
    public static Channel make() {
        return new ChannelProcessor();
    }
}
