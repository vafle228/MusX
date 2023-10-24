package org.musxteam.music.search;

import org.musxteam.music.search.types.ISearchItemsContainer;

import java.io.IOException;
import java.util.List;

public abstract class SearchServiceBase {
    public abstract ISearchItemsContainer searchMusic(String query) throws IOException;
}
