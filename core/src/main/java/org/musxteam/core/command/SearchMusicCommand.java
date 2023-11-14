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
import org.musxteam.music.service.MusicServiceBase;

import java.io.IOException;

public class SearchMusicCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    private HandlingState formSearchResponse(ISearchItem item, IViewFactory viewFactory) {
        SearchViewBase view = viewFactory.getSearchView(
                item.getItemTitle(), item.getItemVideoId(),
                item.getItemChannelTitle(), item.getItemThumbnail()
        );
        return new HandlingState(view, false);
    }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            TextMessageViewBase view = viewFactory.getTextMessageView(
                    RequestReplies.SEARCH_START.getReply()
            ); changeState(new SearchState()); return new HandlingState(view, false);
        }
    }

    class SearchState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            try {
                MusicServiceBase service = request.getUser().musicService;
                ISearchItem item = service.searchMusic(request.getText());
                changeState(new SearchViewState()); return formSearchResponse(item, viewFactory);
            } catch (IOException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), true);
            }
        }
    }

    class SearchViewState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            MusicServiceBase service = request.getUser().musicService;
            try {
                return switch (request.getText()) {
                    case "next" -> formSearchResponse(service.getNextItem(), viewFactory);
                    case "prev" -> formSearchResponse(service.getPrevItem(), viewFactory);
                    default -> new HandlingState(viewFactory.getTextMessageView("Illegal argument"), false);
                };
            } catch (ArrayIndexOutOfBoundsException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), false);
            }
        }
    }
}
