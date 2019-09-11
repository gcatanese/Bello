package com.perosa.bello;

import com.perosa.bello.core.Balancer;
import com.perosa.bello.core.resource.data.HealthCheckClient;
import com.perosa.bello.core.resource.data.ResourceHealthCheck;
import com.perosa.bello.server.DispatchLogic;
import com.perosa.bello.server.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalancerApp.class);

    public static void main(String[] args) {

        new Listener(new DispatchLogic(Balancer.getInstance())).setUp();

        new ResourceHealthCheck(new HealthCheckClient()).start();

        LOGGER.info("Bello is up!");
    }
}
