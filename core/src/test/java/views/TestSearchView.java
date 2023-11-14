package views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.SearchViewBase;

public class TestSearchView extends SearchViewBase {
    public TestSearchView(String title, String videoId, String channelTitle, String thumbnailUrl) {
        super(title, videoId, channelTitle, thumbnailUrl);
    }

    @Override
    public void render(IRequest request) {
        System.out.print(title + "|" + videoId + "|" + channelTitle + "|" + thumbnailUrl);
    }
}
