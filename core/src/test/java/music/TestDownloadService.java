package music;

import org.musxteam.music.download.DownloadServiceBase;
import org.musxteam.music.download.types.MusicInstance;

import java.io.IOException;

public class TestDownloadService extends DownloadServiceBase {
    @Override
    public MusicInstance downloadMusic(String id) throws IOException {
        return new MusicInstance("Test title", "Test thumbnail url", "Test music url");
    }
}
