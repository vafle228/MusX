package org.musxteam.core.views.types;

import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.SearchViewBase;

public interface IViewFactory {
    TextMessageViewBase getTextMessageView(String text);
    DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl);
    SearchViewBase getSearchView(String title, String videoId, String channelTitle, String thumbnailUrl);
}
