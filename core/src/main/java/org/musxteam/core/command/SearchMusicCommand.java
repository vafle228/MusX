package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.CommandBase;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.ICommandState;
import org.musxteam.music.search.YoutubeSearchService;
import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;
import org.musxteam.credentials.YoutubeKeyProvider;

import java.io.IOException;

public class SearchMusicCommand extends CommandBase {
    private final YoutubeSearchService youtubeSearchService;

    public SearchMusicCommand(YoutubeKeyProvider keyProvider) {
        youtubeSearchService = new YoutubeSearchService(keyProvider);
    }

    @Override
    protected ICommandState initStartState() { return new StartState(); }

     class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            changeState(new SearchState());
            return new HandlingState(RequestReplies.SEARCH_START.getReply(), false);
        }
    }

    class SearchState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            try {
                StringBuilder response = new StringBuilder();
                ISearchItemsContainer container = youtubeSearchService.searchMusic(request.getText());

                for (ISearchItem item : container.getSearchItems()) {
                    response.append(item.getItemTitle());
                    response.append(" | ").append(item.getItemVideoId()).append("\n");
                }
                return new HandlingState(response.toString(), true);
            }
            catch (IOException e) { return new HandlingState(e.getMessage(), true); }
        }
    }
}
