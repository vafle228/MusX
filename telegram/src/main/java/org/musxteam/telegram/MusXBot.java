package org.musxteam.telegram;

import org.musxteam.core.RequestHandler;
import org.musxteam.core.requests.TelegramRequest;
import org.musxteam.credentials.TelegramKeyProvider;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class MusXBot extends TelegramLongPollingBot {
    private final TelegramKeyProvider telegramKeyProvider;
    private final RequestHandler requestHandler = new RequestHandler();

    public MusXBot(String[] args) {
        telegramKeyProvider = new TelegramKeyProvider(args);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
        {
            Message message = update.getMessage();
            TelegramRequest request = new TelegramRequest(message);
            requestHandler.startNewCommand(request);

            SendMessage response = new SendMessage();

            response.setChatId(message.getChatId().toString());
            response.setText(requestHandler.handleRequest(request));

            try { execute(response); }
            catch (TelegramApiException e) { e.printStackTrace(); }
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
