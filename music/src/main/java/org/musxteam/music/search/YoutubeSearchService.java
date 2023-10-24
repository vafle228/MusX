package org.musxteam.music.search;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import org.apache.hc.client5.http.fluent.Request;
import org.musxteam.music.search.types.ISearchItemsContainer;
import org.musxteam.music.search.types.YoutubeSearchItemsContainer;

public class YoutubeSearchService extends SearchServiceBase {
    final String searchEndpoint = "https://www.googleapis.com/youtube/v3/search?part=snippet&q={0}";

    @Override
    public ISearchItemsContainer searchMusic(String query) throws IOException {
        String response = Request.get(
                MessageFormat.format(searchEndpoint, encodeURL(query))
        ).execute().returnContent().asString();
        return new Gson().fromJson(response, YoutubeSearchItemsContainer.class);
    }

    private String encodeURL(String query) {
        return URLEncoder.encode(query, StandardCharsets.UTF_8);
    }
}
