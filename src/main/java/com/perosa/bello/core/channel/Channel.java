package com.perosa.bello.core.channel;

import com.perosa.bello.server.InRequest;

public interface Channel {

    String extract(InRequest request);

}
