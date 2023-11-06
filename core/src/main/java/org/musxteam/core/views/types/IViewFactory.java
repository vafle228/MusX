package org.musxteam.core.views.types;

import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.SearchViewBase;

public interface IViewFactory {
    SearchViewBase getSearchView();
    TextMessageViewBase getTextMessageView(String text);
    DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl);
}
