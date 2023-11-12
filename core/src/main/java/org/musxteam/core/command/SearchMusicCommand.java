package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.IRequest;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.SearchViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;
import org.musxteam.music.service.MusicServiceBase;

import java.io.IOException;

public class SearchMusicCommand extends CommandBase {
    private final IViewFactory viewFactory;
    public SearchMusicCommand(IViewFactory viewFactory) { this.viewFactory = viewFactory; }

    @Override
    protected ICommandState initStartState() { return new StartState(); }

    private HandlingState formSearchResponse(ISearchItem item) {
        SearchViewBase view = viewFactory.getSearchView(
                item.getItemTitle(), item.getItemVideoId(),
                item.getItemChannelTitle(), item.getItemThumbnail()
        ); return new HandlingState(view, false);
    }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            TextMessageViewBase view = viewFactory.getTextMessageView(
                    RequestReplies.SEARCH_START.getReply()
            ); changeState(new SearchState()); return new HandlingState(view, false);
        }
    }

    class SearchState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            try {
                MusicServiceBase service = request.getUser().musicService;
                ISearchItem item = service.searchMusic(request.getText());
                changeState(new SearchViewState()); return formSearchResponse(item);
            }
            catch (IOException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), true);
            }
        }
    }

    class SearchViewState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            MusicServiceBase service = request.getUser().musicService;
            try {
                return switch (request.getText()) {
                    case "next" -> formSearchResponse(service.getNextItem());
                    case "prev" -> formSearchResponse(service.getPrevItem());
                    default -> new HandlingState(viewFactory.getTextMessageView("Illegal argument"), false);
                };
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), false);
            }
        }
    }
}
