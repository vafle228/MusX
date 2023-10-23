package org.musxteam.music.search;

import java.io.IOException;

public abstract class SearchServiceBase {
    public abstract String searchMusic(String query) throws IOException;
}
