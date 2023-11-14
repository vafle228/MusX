package org.musxteam.credentials;

public class YoutubeKeyProvider extends ApiKeyProviderBase {
    public YoutubeKeyProvider(String[] args) { super(args); }

    @Override
    public String getKeyName() { return "youtube_apikey"; }
}
