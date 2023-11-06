package org.musxteam.telegram.views;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.IRequest;
import org.musxteam.core.views.HelpCommandViewBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramHelpCommandView extends HelpCommandViewBase {
    private final TelegramLongPollingBot bot;

    public TelegramHelpCommandView(TelegramLongPollingBot bot) { this.bot = bot; }

    @Override
    public void render(IRequest request) {
        try {
            SendMessage response = new SendMessage();
            response.setChatId(request.getChatId());
            response.setText(RequestReplies.HELP.getReply());
            bot.execute(response);
        }
        catch (TelegramApiException e) { e.printStackTrace(System.out); }
    }
}
