package org.musxteam.telegram;

import org.musxteam.core.RequestHandler;
import org.musxteam.credentials.TelegramKeyProvider;
import org.musxteam.telegram.views.TelegramViewFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class MusXBot extends TelegramLongPollingBot {
    private final TelegramKeyProvider telegramKeyProvider;
    private final RequestHandler requestHandler = new RequestHandler();
    private final TelegramViewFactory viewFactory = new TelegramViewFactory(this);

    public MusXBot(String[] args) {
        telegramKeyProvider = new TelegramKeyProvider(args);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
        {
            Message message = update.getMessage();
            TelegramRequest request = new TelegramRequest(message);
            requestHandler.startNewCommand(request, viewFactory);

            requestHandler.handleRequest(request).render(request);
        }
    }
    @Override
    public String getBotUsername() {
        return "MusXBot";
    }
    @Override
    public String getBotToken() {
        return telegramKeyProvider.getApiKey();
    }
}
