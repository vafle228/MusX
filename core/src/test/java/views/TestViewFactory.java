package views;

import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.MusicViewBase;
import org.musxteam.core.views.PlaylistViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.core.views.types.PlaylistView;

import java.util.ArrayList;

public class TestViewFactory implements IViewFactory {
    @Override
    public TextMessageViewBase getTextMessageView(String text) {
        return new TestTextMessageView(text);
    }

    @Override
    public PlaylistViewBase getPlaylistView(ArrayList<PlaylistView> playlistViews) {
        return null;
    }

    @Override
    public DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl) {
        return new TestDownloadView(title, audioUrl, thumbnailUrl);
    }
    @Override
    public MusicViewBase getSearchView(String title, String videoId, String channelTitle, String thumbnailUrl) {
        return new TestSearchView(title, videoId, channelTitle, thumbnailUrl);
    }
}
