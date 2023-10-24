package org.musxteam.music.service;

import org.musxteam.music.download.YoutubeDownloadService;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.search.YoutubeSearchService;
import org.musxteam.music.search.types.ISearchItemsContainer;

import java.io.IOException;

public class YoutubeMusicService implements IMusicService {
    YoutubeSearchService searchService = new YoutubeSearchService();
    YoutubeDownloadService downloadService = new YoutubeDownloadService();

    @Override
    public MusicInstance downloadMusic(String id) throws IOException {
        return downloadService.downloadMusic(id);
    }
    @Override
    public ISearchItemsContainer searchMusic(String query) throws IOException {
        return searchService.searchMusic(query);
    }
}
