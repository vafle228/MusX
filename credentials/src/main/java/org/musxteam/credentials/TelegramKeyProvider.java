package org.musxteam.credentials;

public class TelegramKeyProvider extends ApiKeyProviderBase {
    public TelegramKeyProvider(String[] args) { super(args); }

    @Override
    public String getKeyName() {
        return "telegram_apikey";
    }
}
