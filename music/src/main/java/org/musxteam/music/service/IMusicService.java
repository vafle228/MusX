package org.musxteam.music.service;

import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.search.types.ISearchItemsContainer;

import java.io.IOException;

public interface IMusicService {
    MusicInstance downloadMusic(String id) throws IOException;
    ISearchItemsContainer searchMusic(String query) throws IOException;
}
