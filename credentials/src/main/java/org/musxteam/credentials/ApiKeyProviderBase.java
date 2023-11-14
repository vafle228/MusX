package org.musxteam.credentials;

import java.util.Objects;

public abstract class ApiKeyProviderBase {
    private String apiKey = null;

    public ApiKeyProviderBase(String[] args) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        setApiKey(args);
    }

    public abstract String getKeyName();
    public String getApiKey() { return apiKey; }

    private void setApiKey(String[] arguments) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        for (String argument : arguments) {
            String[] params = argument.split("=");
            if (Objects.equals(getKeyName(), params[0])) apiKey = params[1];
        }

        if (Objects.equals(apiKey, null)) throw new IllegalArgumentException();
    }
}
