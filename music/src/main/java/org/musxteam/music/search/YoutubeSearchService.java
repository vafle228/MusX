package org.musxteam.music.search;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.apache.hc.client5.http.fluent.Request;

import java.io.IOException;

public class YoutubeSearchService extends SearchServiceBase {
    @Override
    public String searchMusic(String query) throws IOException {

        return Request.get("https://www.google.ru/")
                .execute()
                .returnContent().asString();
    }
}
