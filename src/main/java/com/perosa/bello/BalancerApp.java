package com.perosa.bello;

import com.perosa.bello.core.balancer.Balancer;
import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.healthcheck.HealthCheckClient;
import com.perosa.bello.core.resource.healthcheck.ResourceHealthCheck;
import com.perosa.bello.core.resource.metrics.Gauges;
import com.perosa.bello.core.resource.metrics.SessionTrackerThread;
import com.perosa.bello.server.DispatchLogic;
import com.perosa.bello.server.Listener;
import io.prometheus.client.hotspot.DefaultExports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalancerApp.class);

    public static void main(String[] args) {

        new Listener(new DispatchLogic(Balancer.getInstance())).setUp();

        new ResourceHealthCheck(new HealthCheckClient()).start();

        new SessionTrackerThread().startTracking();

        DefaultExports.initialize();

        LOGGER.info("Bello is up! (" + new Env() + ")");
    }
}
