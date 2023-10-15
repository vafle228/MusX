package org.musxteam.core.requests;

public class TestRequest implements IRequest{
    @Override
    public String getText() {
        return "Test text";
    }

    @Override
    public String getUserId() {
        return "Test user";
    }
}
