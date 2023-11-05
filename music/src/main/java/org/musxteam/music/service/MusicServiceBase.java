package org.musxteam.music.service;

import org.musxteam.music.download.DownloadServiceBase;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.search.SearchServiceBase;
import org.musxteam.music.search.types.ISearchItemsContainer;

import java.io.IOException;

public abstract class MusicServiceBase {
    SearchServiceBase searchService = initSearchService();
    DownloadServiceBase downloadService = initDownloadService();

    protected abstract SearchServiceBase initSearchService();
    protected abstract  DownloadServiceBase initDownloadService();

    public MusicInstance downloadMusic(String id) throws IOException {
        return downloadService.downloadMusic(id);
    }
    public ISearchItemsContainer searchMusic(String query) throws IOException {
        return searchService.searchMusic(query);
    }
}
