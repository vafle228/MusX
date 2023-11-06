package org.musxteam.telegram.views;

import org.musxteam.core.views.*;
import org.musxteam.core.views.types.IViewFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class TelegramViewFactory implements IViewFactory {
    private final TelegramLongPollingBot bot;
    public TelegramViewFactory(TelegramLongPollingBot bot) { this.bot = bot; }

    @Override
    public SearchViewBase getSearchView() {
        return null;
    }
    @Override
    public TextMessageViewBase getTextMessageView(String text) {
        return new TelegramTextMessageView(text, bot);
    }
    @Override
    public DownloadViewBase getDownloadView(String title, String audioUrl, String thumbnailUrl) {
        return new TelegramDownloadView(title, audioUrl, thumbnailUrl, bot);
    }
}
