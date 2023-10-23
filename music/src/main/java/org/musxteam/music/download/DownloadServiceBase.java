package org.musxteam.music.download;

import java.io.IOException;

public abstract class DownloadServiceBase {
    public abstract String downloadMusic(String id) throws IOException;
}
