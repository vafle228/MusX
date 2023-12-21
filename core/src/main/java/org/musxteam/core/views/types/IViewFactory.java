package org.musxteam.core.views.types;

import org.musxteam.core.views.*;

import java.util.ArrayList;

public interface IViewFactory {
    TextMessageViewBase getTextMessageView(String text);
    PlaylistViewBase getPlaylistView(ArrayList<PlaylistView> playlistViews);
    DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl);
    SearchViewBase getSearchView(String title, String videoId, String channelTitle, String thumbnailUrl);
    PlaylistTrackViewBase getPlaylistTrackView(String title, String videoId, String entryId, String playlistId, String channelTitle, String thumbnailUrl);
}
