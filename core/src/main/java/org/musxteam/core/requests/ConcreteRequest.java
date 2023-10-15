package org.musxteam.core.requests;

public class ConcreteRequest implements IRequest {
    private final String request;
    public ConcreteRequest(String request) {
        this.request = request;
    }

    @Override
    public String getText() {
        return request;
    }
    @Override
    public String getUserId() {
        return "1";
    }
}
