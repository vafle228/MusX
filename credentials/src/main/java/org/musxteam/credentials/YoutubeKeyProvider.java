package org.musxteam.credentials;

public class YoutubeKeyProvider extends ApiKeyProviderBase {
    public YoutubeKeyProvider(String[] args) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        super(args);
    }

    @Override
    public String getKeyName() { return "youtube_apikey"; }
}
