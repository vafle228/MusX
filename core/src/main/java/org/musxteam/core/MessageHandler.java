package org.musxteam.core;

import org.musxteam.core.music.service.ConcreteMusicService;
import org.musxteam.core.music.service.IMusicService;

public class MessageHandler {
    IMusicService service;
    public String handleDownload(String id) {
        return service.downloadMusic(id);
    }
    public String handleSearch(String query) {
        return service.searchMusic(query);
    }
}
