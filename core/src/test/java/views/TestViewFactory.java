package views;

import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.SearchViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.types.IViewFactory;

public class TestViewFactory implements IViewFactory {
    @Override
    public TextMessageViewBase getTextMessageView(String text) {
        return new TestTextMessageView(text);
    }
    @Override
    public DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl) {
        return new TestDownloadView(title, audioUrl, thumbnailUrl);
    }
    @Override
    public SearchViewBase getSearchView(String title, String videoId, String channelTitle, String thumbnailUrl) {
        return new TestSearchView(title, videoId, channelTitle, thumbnailUrl);
    }
}
