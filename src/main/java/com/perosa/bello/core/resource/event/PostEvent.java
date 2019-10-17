package com.perosa.bello.core.resource.event;

import com.perosa.bello.server.InRequest;

public class PostEvent implements Event {

    private InRequest request;
    private String target;

    public PostEvent(InRequest request, String target) {
        this.request = request;
        this.target = target;
    }

    @Override
    public String getName() {
        return "PostEvent";
    }

    public InRequest getRequest() {
        return request;
    }

    public void setRequest(InRequest request) {
        this.request = request;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "PostEvent[" +
                "target:" + target +
                "," + request +
                "]";
    }
}
