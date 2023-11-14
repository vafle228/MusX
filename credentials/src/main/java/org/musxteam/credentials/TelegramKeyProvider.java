package org.musxteam.credentials;

public class TelegramKeyProvider extends ApiKeyProviderBase {
    public TelegramKeyProvider(String[] args) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        super(args);
    }

    @Override
    public String getKeyName() { return "telegram_apikey"; }
}
