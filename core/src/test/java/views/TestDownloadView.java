package views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.DownloadViewBase;

public class TestDownloadView extends DownloadViewBase {
    public TestDownloadView(String title, String audioUrl, String thumbnailUrl) {
        super(title, audioUrl, thumbnailUrl);
    }

    @Override
    public void render(IRequest request) {
        System.out.println(title + " " + audioUrl + " " + thumbnailUrl);
    }
}
