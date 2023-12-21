package org.musxteam.telegram.views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.PlaylistTrackViewBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TelegramPlaylistTrackView extends PlaylistTrackViewBase {
    private final TelegramLongPollingBot bot;

    public TelegramPlaylistTrackView(String title, String videoId, String entryId, String playlistId,
                                     String channelTitle, String thumbnailUrl, TelegramLongPollingBot bot) {
        super(title, videoId, entryId, playlistId, channelTitle, thumbnailUrl); this.bot = bot;
    }

    @Override
    public void render(IRequest request) {
        try {
            URL thumbnail = new URI(thumbnailUrl).toURL();
            InputFile thumbnailFile = new InputFile(thumbnail.openStream(), "thumbnail.jpeg");

            SendPhoto response = SendPhoto.builder()
                    .photo(thumbnailFile).chatId(request.getChatId())
                    .caption(title + "\n" + "Channel: " + channelTitle).build();
            response.setReplyMarkup(initMessageKeyboard()); this.bot.execute(response);
        }
        catch (URISyntaxException | IOException ex) {
            new TelegramTextMessageView(ex.toString(), bot).render(request);
        }
        catch (TelegramApiException ex) { ex.printStackTrace(System.out); }
    }

    private InlineKeyboardMarkup initMessageKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(InlineKeyboardButton.builder().text("Previous").callbackData("prev").build());
        firstRow.add(InlineKeyboardButton.builder().text("Next").callbackData("next").build());

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(InlineKeyboardButton.builder().text("Download").callbackData("/download " + videoId).build());

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        thirdRow.add(InlineKeyboardButton.builder().text("Remove from playlist").callbackData("/playlist_del " + playlistId + " " + entryId).build());

        keyboard.add(firstRow); keyboard.add(secondRow); keyboard.add(thirdRow);markup.setKeyboard(keyboard);

        return markup;
    }
}
