package org.musxteam.core.views.types;

import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.PlaylistViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.MusicViewBase;

import java.util.ArrayList;

public interface IViewFactory {
    TextMessageViewBase getTextMessageView(String text);
    PlaylistViewBase getPlaylistView(ArrayList<PlaylistView> playlistViews);
    DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl);
    MusicViewBase getSearchView(String title, String videoId, String channelTitle, String thumbnailUrl);
}
