package org.musxteam.telegram.views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.PlaylistViewBase;
import org.musxteam.core.views.types.PlaylistView;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;

public class TelegramPlaylistView extends PlaylistViewBase {
    private final TelegramLongPollingBot bot;

    public TelegramPlaylistView(ArrayList<PlaylistView> playlistViews, TelegramLongPollingBot bot) {
        super(playlistViews); this.bot = bot;
    }

    @Override
    public void render(IRequest request) {

    }
}
