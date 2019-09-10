package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.SessionCache;
import com.perosa.bello.core.resource.channel.Channel;
import com.perosa.bello.core.resource.channel.ChannelFactory;
import com.perosa.bello.core.resource.data.ResourceCache;
import com.perosa.bello.server.InRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreBalancer.class);

    private SessionCache sessionCache = SessionCache.make();

    abstract ResourceHost findNext(List<ResourceHost> hosts);

    public String findTarget(InRequest request) {

        String sessionId = extractSessionId(request);

        if (sessionId != null) {
            String host = get(sessionId);
            if (host != null) {
                return host;
            }
        }


        List<ResourceHost> list = ResourceCache.getResourceHosts();

        List<ResourceHost> availableHosts = getAvailableHosts(list);

        ResourceHost resourceHost = findNext(availableHosts);

        String target = resourceHost.getHost();

        put(sessionId, target);

        LOGGER.debug("--->" + target);

        return target;
    }

    List<ResourceHost> getAvailableHosts(List<ResourceHost> list) {
        List<ResourceHost> hosts = list.stream()
                .filter(h -> h.isAvailable())
                .collect(Collectors.toList());

        if (hosts.isEmpty()) {
            throw new RuntimeException(("No targets available"));
        }

        return hosts;
    }

    String get(String sessionId) {
        return sessionCache.get(sessionId);
    }

    void put(String sessionId, String host) {
        sessionCache.put(sessionId, host);
    }

    Channel getChannel(String payload) {
        return ChannelFactory.make(payload);
    }

    String extractSessionId(InRequest request) {
        String sessionId = null;

        if (request.getPayload() != null) {
            sessionId = getChannel(request.getPayload()).extractSessionId();
        }

        return sessionId;
    }

}
