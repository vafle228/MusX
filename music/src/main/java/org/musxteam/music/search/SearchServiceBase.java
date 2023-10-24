package org.musxteam.music.search;

import java.io.IOException;
import org.musxteam.music.search.types.ISearchItemsContainer;

public abstract class SearchServiceBase {
    public abstract ISearchItemsContainer searchMusic(String query) throws IOException;
}
