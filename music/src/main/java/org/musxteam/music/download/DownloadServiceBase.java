package org.musxteam.music.download;

import org.musxteam.music.types.MusicInstance;

import java.io.IOException;

public abstract class DownloadServiceBase {
    public abstract MusicInstance downloadMusic(String id) throws IOException;
}
