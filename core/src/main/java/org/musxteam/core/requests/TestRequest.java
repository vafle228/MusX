package org.musxteam.core.requests;

import java.text.MessageFormat;

public class TestRequest implements IRequest {
    private final String request;

    public TestRequest(String request) {
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
    @Override
    public String toString() {
        return MessageFormat.format("Request[text={0}, user_id={1}]", getText(), getUserId());
    }
}
