package org.musxteam.music.search;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.musxteam.credentials.YoutubeKeyProvider;
import org.musxteam.music.search.types.ISearchItemsContainer;
import org.musxteam.music.search.types.YoutubeSearchIdItemContainer;
import org.musxteam.music.search.types.YoutubeSearchItemsContainer;

public class YoutubeSearchService extends SearchServiceBase {
    private final YoutubeKeyProvider youtubeKeyProvider;

    private static final String SEARCH_ID_ENDPOINT = "https://www.googleapis.com/youtube/v3/videos?part=snippet&id={0}&key={1}";
    private static final String SEARCH_ENDPOINT = "https://www.googleapis.com/youtube/v3/search?part=snippet&q={0}&type=video&key={1}";

    public YoutubeSearchService(YoutubeKeyProvider keyProvider) { youtubeKeyProvider = keyProvider; }

    @Override
    protected ISearchItemsContainer executeSearchId(String id) throws IOException {
        Content rawResponse = Request.get(formatApiIdRequest(id)).execute().returnContent();
        return new Gson().fromJson(rawResponse.asString(), YoutubeSearchIdItemContainer.class);
    }

    @Override
    protected ISearchItemsContainer executeSearchQuery(String query) throws IOException {
        Content rawResponse = Request.get(formatApiRequest(query)).execute().returnContent();
        return new Gson().fromJson(rawResponse.asString(), YoutubeSearchItemsContainer.class);
    }

    private String formatApiIdRequest(String id) {
        return MessageFormat.format(SEARCH_ID_ENDPOINT, encodeURL(id), youtubeKeyProvider.getApiKey());
    }
    private String formatApiRequest(String query) {
        return MessageFormat.format(SEARCH_ENDPOINT, encodeURL(query), youtubeKeyProvider.getApiKey());
    }
    private String encodeURL(String query) { return URLEncoder.encode(query, StandardCharsets.UTF_8); }
}
