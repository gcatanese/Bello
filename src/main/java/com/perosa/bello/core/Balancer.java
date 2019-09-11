package com.perosa.bello.core;

import com.perosa.bello.server.InRequest;

public interface Balancer {

    String findTarget(InRequest request);

    static Balancer getInstance() {
        return new RoundRobinBalancer();
    }
}
