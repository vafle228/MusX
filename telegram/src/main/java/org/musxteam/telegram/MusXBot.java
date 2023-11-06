package org.musxteam.telegram;

import org.musxteam.core.RequestHandler;
import org.musxteam.credentials.TelegramKeyProvider;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.net.URL;


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

            // SendMessage response = new SendMessage();
            SendAudio response = new SendAudio();

            try {
                URL url = new URL("https://i.ytimg.com/vi/dQw4w9WgXcQ/maxresdefault.jpg");
                InputFile thumbnail = new InputFile(url.openStream(), "thumbnail.jpeg");

                URL musicUrl = new URL("https://apiyoutube.cc/m4a/QcXgW9w4wQd::8ec40ebde81a193756eba5702da565bf::1699199393::no::ur");
                InputFile audio = new InputFile(musicUrl.openStream(), "audio.m4a");

                response.setAudio(audio);
                response.setThumb(thumbnail);
                response.setTitle("Never gonna give u up");
                response.setPerformer("Rick Astley");
                response.setChatId(message.getChatId().toString());

                execute(response);
            }
            catch (Exception e) { e.printStackTrace(); }
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
