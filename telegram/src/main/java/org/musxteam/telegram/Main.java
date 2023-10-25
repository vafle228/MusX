package org.musxteam.telegram;

import java.util.Objects;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.musxteam.core.RequestHandler;
import org.musxteam.core.command.EchoCommand;
import org.musxteam.core.command.HelpCommand;
import org.musxteam.core.requests.ConcreteRequest;

import org.musxteam.credentials.TelegramKeyProvider;
import org.musxteam.credentials.YoutubeKeyProvider;
import org.musxteam.music.download.YoutubeDownloadService;
import org.musxteam.music.search.YoutubeSearchService;
import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MusXBot(args));
    }
}