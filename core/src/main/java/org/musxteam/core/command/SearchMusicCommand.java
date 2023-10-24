package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.CommandBase;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.ICommandState;
import org.musxteam.music.search.YoutubeSearchService;
import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;

import java.io.IOException;

public class SearchMusicCommand extends CommandBase {
    private YoutubeSearchService youtubeSearchService = new YoutubeSearchService();

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
                String response = "";
                ISearchItemsContainer container = youtubeSearchService.searchMusic(request.getText());

                for (ISearchItem item : container.getSearchItems()) {
                    response += item.getItemTitle();
                    response += " | " + item.getItemVideoId() + "\n";
                }

                return new HandlingState(response, true);
            }
            catch (IOException e) {
                return new HandlingState(e.getMessage(), true);
            }
        }
    }
}
