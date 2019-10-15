package com.perosa.bello.core.resource.session;

import java.time.LocalDateTime;

public class SessionInfo {

    private String id;
    private String host;
    private LocalDateTime date = LocalDateTime.now();
    private String channel;

    public SessionInfo() {
    }

    public SessionInfo(String id, String host) {
        this.id = id;
        this.host = host;
        this.channel = "n/a";
    }

    public SessionInfo(String id, String host, String channel) {
        this.id = id;
        this.host = host;
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "id=" + id +
                ",host=" + host +
                ",channel=" + channel +
                '}';
    }
}
