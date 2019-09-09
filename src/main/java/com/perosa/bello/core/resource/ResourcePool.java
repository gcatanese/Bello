package com.perosa.bello.core.resource;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool {

    private String path;
    private List<ResourceHost> resourceHostList = new ArrayList<>();

    public ResourcePool() {
    }

    public ResourcePool(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ResourceHost> getResourceHostList() {
        return resourceHostList;
    }

    public void setResourceHostList(List<ResourceHost> resourceHostList) {
        this.resourceHostList = resourceHostList;
    }

    @Override
    public String toString() {
        return "ResourcePool{" +
                "path='" + path + '\'' +
                ", resourceHostList=" + resourceHostList +
                '}';
    }
}
