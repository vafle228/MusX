package org.musxteam.music.service;

import org.musxteam.credentials.YoutubeKeyProvider;
import org.musxteam.music.download.DownloadServiceBase;
import org.musxteam.music.download.YoutubeDownloadService;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.search.SearchServiceBase;
import org.musxteam.music.search.YoutubeSearchService;
import org.musxteam.music.search.types.ISearchItemsContainer;

import java.io.IOException;

public class YoutubeMusicService extends MusicServiceBase {
    private static YoutubeKeyProvider keyProvider;

    @Override
    protected DownloadServiceBase initDownloadService() {
        return new YoutubeDownloadService();
    }
    @Override
    protected SearchServiceBase initSearchService() {
        return new YoutubeSearchService(keyProvider);
    }

    public static void initYoutubeKeyProvider(String[] args) {
        keyProvider = new YoutubeKeyProvider(args);
    }
}
