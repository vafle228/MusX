package org.musxteam.telegram.views;

import org.musxteam.core.views.*;
import org.musxteam.core.views.types.ICommandViewFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class TelegramCommandViewFactory implements ICommandViewFactory {
    private final TelegramLongPollingBot bot;
    public TelegramCommandViewFactory(TelegramLongPollingBot bot) { this.bot = bot; }

    @Override
    public HelpCommandViewBase getHelpCommandView() {
        return new TelegramHelpCommandView(bot);
    }

    @Override
    public SearchCommandViewBase getSearchCommandView() {
        return null;
    }

    @Override
    public DownloadCommandViewBase getDownloadCommandView() {
        return null;
    }
}
