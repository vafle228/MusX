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

public class Main {
    private static final RequestHandler requestHandler = new RequestHandler();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        YoutubeKeyProvider youtubeKeyProvider = new YoutubeKeyProvider(args);
        System.out.println(youtubeKeyProvider.getApiKey());

        TelegramKeyProvider telegramKeyProvider = new TelegramKeyProvider(args);
        System.out.println(telegramKeyProvider.getApiKey());

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

        boolean run = false;
        while (run) {
            String input = br.readLine();
            ConcreteRequest request = new ConcreteRequest(input);

            if (Objects.equals(input, "/help"))
                requestHandler.startNewCommand(request, new HelpCommand());

            if (Objects.equals(input, "/echo"))
                requestHandler.startNewCommand(request, new EchoCommand());

            if (Objects.equals(input, "/break")) break;

            System.out.println(requestHandler.handleRequest(request));
        }
    }
}