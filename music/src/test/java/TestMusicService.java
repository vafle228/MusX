import org.musxteam.music.download.DownloadServiceBase;
import org.musxteam.music.search.SearchServiceBase;
import org.musxteam.music.service.MusicServiceBase;

public class TestMusicService extends MusicServiceBase {
    @Override
    protected SearchServiceBase initSearchService() {
        return new TestSearchService();
    }

    @Override
    protected DownloadServiceBase initDownloadService() {
        return new TestDownloadService();
    }
}
