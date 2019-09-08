package com.perosa.bello.core.resource;

public class ResourceHost {

    private String host;
    private int available;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ResourceHost{" +
                "host='" + host + '\'' +
                ", available=" + available +
                '}';
    }
}
