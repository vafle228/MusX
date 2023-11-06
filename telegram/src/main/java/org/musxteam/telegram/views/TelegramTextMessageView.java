package org.musxteam.telegram.views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.TextMessageViewBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramTextMessageView extends TextMessageViewBase {
    private final TelegramLongPollingBot bot;
    public TelegramTextMessageView(String text, TelegramLongPollingBot bot) { super(text); this.bot = bot; }

    @Override
    public void render(IRequest request) {
        try {
            SendMessage response = new SendMessage();
            response.setChatId(request.getChatId());
            response.setText(text); bot.execute(response);
        }
        catch (TelegramApiException e) { e.printStackTrace(System.out); }
    }
}
