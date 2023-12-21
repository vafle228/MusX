package org.musxteam.telegram.views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.PlaylistViewBase;
import org.musxteam.core.views.types.PlaylistView;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramPlaylistView extends PlaylistViewBase {
    private final TelegramLongPollingBot bot;

    public TelegramPlaylistView(String title, ArrayList<PlaylistView> playlistViews, TelegramLongPollingBot bot) {
        super(title, playlistViews); this.bot = bot;
    }

    @Override
    public void render(IRequest request) {
        if (!playlistViews.isEmpty())
            handlePlaylistView(request);
        else handleZeroPlaylistView(request);
    }

    private void handlePlaylistView(IRequest request) {
        try {
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

            for (int i = 0; i < playlistViews.size(); i += 3) {
                List<InlineKeyboardButton> playlistRow = new ArrayList<>();

                for (int j = i; j < playlistViews.size() && j < i + 3; j++)
                    playlistRow.add(InlineKeyboardButton.builder()
                            .text(playlistViews.get(j).title())
                            .callbackData(playlistViews.get(j).id()).build());
                keyboard.add(playlistRow);
            }

            SendMessage response = SendMessage.builder()
                    .chatId(request.getChatId()).text(title).build();
            markup.setKeyboard(keyboard); response.setReplyMarkup(markup); this.bot.execute(response);
        }
        catch (TelegramApiException ex) { ex.printStackTrace(System.out); }
    }
    private void handleZeroPlaylistView(IRequest request) {
        try {
            SendMessage response = SendMessage.builder()
                    .chatId(request.getChatId())
                    .text("There no playlist!").build();
            this.bot.execute(response);
        }
        catch (TelegramApiException ex) { ex.printStackTrace(System.out); }
    }
}
