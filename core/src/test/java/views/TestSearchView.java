package views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.MusicViewBase;

public class TestSearchView extends MusicViewBase {
    public TestSearchView(String title, String videoId, String channelTitle, String thumbnailUrl) {
        super(title, videoId, channelTitle, thumbnailUrl);
    }

    @Override
    public void render(IRequest request) {
        System.out.print(title + "|" + videoId + "|" + channelTitle + "|" + thumbnailUrl);
    }
}
