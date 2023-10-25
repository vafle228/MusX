package org.musxteam.telegram;

import org.musxteam.core.RequestHandler;
import org.musxteam.core.command.DownloadMusicCommand;
import org.musxteam.core.command.EchoCommand;
import org.musxteam.core.command.HelpCommand;
import org.musxteam.core.command.SearchMusicCommand;
import org.musxteam.core.requests.TelegramRequest;
import org.musxteam.credentials.TelegramKeyProvider;
import org.musxteam.credentials.YoutubeKeyProvider;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

public class MusXBot extends TelegramLongPollingBot {
    private final YoutubeKeyProvider youtubeKeyProvider;
    private final TelegramKeyProvider telegramKeyProvider;
    private final RequestHandler requestHandler = new RequestHandler();

    public MusXBot(String[] args) {
        youtubeKeyProvider = new YoutubeKeyProvider(args);
        telegramKeyProvider = new TelegramKeyProvider(args);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
        {
            Message message = update.getMessage();
            TelegramRequest request = new TelegramRequest(message);

            if (Objects.equals(request.getText(), "/help"))
                requestHandler.startNewCommand(request, new HelpCommand());

            if (Objects.equals(request.getText(), "/echo"))
                requestHandler.startNewCommand(request, new EchoCommand());

            if (Objects.equals(request.getText(), "/search"))
                requestHandler.startNewCommand(request, new SearchMusicCommand(youtubeKeyProvider));

            if (Objects.equals(request.getText(), "/download"))
                requestHandler.startNewCommand(request, new DownloadMusicCommand());

            SendMessage response = new SendMessage();

            response.setChatId(message.getChatId());
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
