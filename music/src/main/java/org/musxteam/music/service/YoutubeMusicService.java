package org.musxteam.music.service;

import org.musxteam.credentials.YoutubeKeyProvider;
import org.musxteam.music.download.DownloadServiceBase;
import org.musxteam.music.download.YoutubeDownloadService;
import org.musxteam.music.search.SearchServiceBase;
import org.musxteam.music.search.YoutubeSearchService;

public class YoutubeMusicService extends MusicServiceBase {
    private static YoutubeKeyProvider keyProvider;

    @Override
    protected DownloadServiceBase initDownloadService() { return new YoutubeDownloadService(); }
    @Override
    protected SearchServiceBase initSearchService() { return new YoutubeSearchService(keyProvider); }

    public static void initYoutubeKeyProvider(String[] args) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        keyProvider = new YoutubeKeyProvider(args);
    }
}
