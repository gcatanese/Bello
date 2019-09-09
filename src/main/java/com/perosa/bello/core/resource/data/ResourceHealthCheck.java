package com.perosa.bello.core.resource.data;

import com.perosa.bello.core.resource.ResourceHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class ResourceHealthCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceHealthCheck.class);

    private HealthCheckClient healthCheckClient = null;

    public ResourceHealthCheck(HealthCheckClient healthCheckClient) {
        this.healthCheckClient = healthCheckClient;
    }

    public void start() {

        Timer timer = new Timer("healthCheckThread");
        final long INTERVAL = 1000L * 20;

        TimerTask task = new TimerTask() {
            public void run() {
                runHealthCheck();
            }
        };

        timer.schedule(task, 2000L, INTERVAL);

    }

    void runHealthCheck() {
        ResourceCache.getResourceHosts().stream()
                .forEach(h -> ping(h));

    }

    void ping(ResourceHost resourceHost) {

        try {
            int responseCode = healthCheckClient.ping(getHealthCheckUrl(resourceHost));

            if (responseCode == 200) {
                ResourceCache.setAsAvailable(resourceHost.getHost());
            } else {
                ResourceCache.setAsUnavailable(resourceHost.getHost());
            }

        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            ResourceCache.setAsUnavailable(resourceHost.getHost());
        }
    }

    String getHealthCheckUrl(ResourceHost resourceHost) {
        return "http://" + resourceHost.getHost() + resourceHost.getHealthCheck();
    }

}
