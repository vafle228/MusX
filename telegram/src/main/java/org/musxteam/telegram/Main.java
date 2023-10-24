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
    private static final RequestHandler requestHandler = new RequestHandler();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        YoutubeKeyProvider youtubeKeyProvider = new YoutubeKeyProvider(args);
        System.out.println(youtubeKeyProvider.getApiKey());

        TelegramKeyProvider telegramKeyProvider = new TelegramKeyProvider(args);
        System.out.println(telegramKeyProvider.getApiKey());

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MusXBot(args));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // YoutubeSearchService youtubeSearchService = new YoutubeSearchService();

        // YoutubeDownloadService youtubeDownloadService = new YoutubeDownloadService();
        // System.out.println(youtubeDownloadService.downloadMusic("BciS5krYL80"));

        // ISearchItemsContainer container = youtubeSearchService.searchMusic("Believer");

/*        for (ISearchItem item : container.getSearchItems()) {
            System.out.println(item.getItemTitle());
            System.out.println(item.getItemVideoId());
            System.out.println(item.getItemDescription());
            System.out.println(item.getItemChannelTitle());
            System.out.println("--------------------------------");
        }*/
    }
}