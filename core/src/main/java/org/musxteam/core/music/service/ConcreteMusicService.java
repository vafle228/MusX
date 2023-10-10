package org.musxteam.core.music.service;

import org.musxteam.core.music.download.ConcreteDownloadService;
import org.musxteam.core.music.search.ConcreteSearchService;

public class ConcreteMusicService implements IMusicService {
    ConcreteSearchService searchService = new ConcreteSearchService();
    ConcreteDownloadService downloadService = new ConcreteDownloadService();

    @Override
    public String downloadMusic(String id) {
        return downloadService.downloadMusic(id);
    }

    @Override
    public String searchMusic(String query) {
        return searchService.searchMusic(query);
    }
}
