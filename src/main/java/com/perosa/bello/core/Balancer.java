package com.perosa.bello.core;

public interface Balancer {

    String findTarget(String host);

    static Balancer make() {
        return new RoundRobinBalancer();
    }
}
