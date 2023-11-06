package org.musxteam.telegram.views;

import java.net.URI;
import java.net.URL;

import java.io.IOException;
import java.net.URISyntaxException;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.DownloadViewBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramDownloadView extends DownloadViewBase {
    private final TelegramLongPollingBot bot;
    public TelegramDownloadView(String title, String audioUrl, String thumbnailUrl, TelegramLongPollingBot bot) {
        super(title, audioUrl, thumbnailUrl); this.bot = bot;
    }

    @Override
    public void render(IRequest request) {
        try {
            URL audio = new URI(audioUrl).toURL();
            URL thumbnail = new URI(thumbnailUrl).toURL();

            InputFile audioFile = new InputFile(audio.openStream(), "audio.m4a");
            InputFile thumbnailFile = new InputFile(thumbnail.openStream(), "thumbnail.jpeg");

            SendAudio response = SendAudio.builder()
                    .thumb(thumbnailFile).audio(audioFile)
                    .title(title).chatId(request.getChatId()).build();
            bot.execute(response);
        }
        catch (URISyntaxException | IOException ex) {
            new TelegramTextMessageView(ex.toString(), bot).render(request);
        }
        catch (TelegramApiException ex) { ex.printStackTrace(System.out); }
    }
}
