package org.musxteam.telegram.views;

import org.musxteam.core.views.*;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.core.views.types.PlaylistView;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;

public class TelegramViewFactory implements IViewFactory {
    private final TelegramLongPollingBot bot;
    public TelegramViewFactory(TelegramLongPollingBot bot) { this.bot = bot; }

    @Override
    public SearchViewBase getSearchView(String title, String videoId, String channelTitle, String thumbnailUrl) {
        return new TelegramSearchView(title, videoId, channelTitle, thumbnailUrl, bot);
    }
    @Override
    public PlaylistTrackViewBase getPlaylistTrackView(String title, String videoId, String entryId, String channelTitle, String thumbnailUrl) {
        return new TelegramPlaylistTrackView(title, videoId, entryId, channelTitle, thumbnailUrl, bot);
    }
    @Override
    public TextMessageViewBase getTextMessageView(String text) {
        return new TelegramTextMessageView(text, bot);
    }
    @Override
    public PlaylistViewBase getPlaylistView(ArrayList<PlaylistView> playlistViews) {
        return new TelegramPlaylistView(playlistViews, bot);
    }
    @Override
    public DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl) {
        return new TelegramDownloadView(title, audioUrl, thumbnailUrl, bot);
    }
}
