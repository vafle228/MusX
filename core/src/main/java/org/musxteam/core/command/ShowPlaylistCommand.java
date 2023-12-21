package org.musxteam.core.command;

import org.musxteam.core.IRequest;
import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.PlaylistTrackViewBase;
import org.musxteam.core.views.PlaylistViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.core.views.types.PlaylistView;
import org.musxteam.database.managers.PlayListManager;
import org.musxteam.database.models.MusicEntryModel;
import org.musxteam.database.models.PlayListModel;

import java.util.ArrayList;

public class ShowPlaylistCommand extends CommandBase {
    private int currentElement;
    private PlayListModel playlist;

    @Override
    protected ICommandState initStartState() { return new StartState(); }

    private HandlingState formResponse(MusicEntryModel entry, IViewFactory viewFactory) {
        PlaylistTrackViewBase view = viewFactory.getPlaylistTrackView(
                entry.title(), entry.videoId(), Integer.toString(entry.id()),
                entry.channelTitle(), entry.getThumbnailUrl()
        );
        return new HandlingState(view, false);
    }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            ArrayList<PlaylistView> views = new ArrayList<>();

            for (PlayListModel playlist : PlayListManager.getAllUserPlaylists(request.getUser().getId())) {
                views.add(new PlaylistView(playlist.title(), playlist.id()));
            }
            PlaylistViewBase view = viewFactory.getPlaylistView(views);

            changeState(new GetPlaylistState()); return new HandlingState(view, false);
        }
    }

    class GetPlaylistState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            try {
                int playlistId = Integer.parseInt(request.getText());
                ArrayList<PlayListModel> entry = PlayListManager.selectPlaylist(playlistId);

                if (!entry.isEmpty()) {
                    playlist = entry.getFirst();

                    if (!playlist.musicEntries().isEmpty()) {
                        changeState(new PlaylistSearchState());
                        return formResponse(playlist.musicEntries().get(currentElement), viewFactory);
                    }

                    return new PlaylistEmptyState().handleRequest(request, viewFactory);
                }
                return new HandlingState(viewFactory.getTextMessageView(
                        RequestReplies.ILLEGAL_PLAYLIST_ID.getReply()), false);
            }
            catch (IllegalArgumentException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), true);
            }
        }
    }

    class PlaylistEmptyState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            TextMessageViewBase view = viewFactory.getTextMessageView(
                    RequestReplies.EMPTY_PLAYLIST.getReply()
            );
            return new HandlingState(view, true);
        }
    }

    class PlaylistSearchState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            try {
                return switch (request.getText()) {
                    case "next" -> formResponse(playlist.musicEntries().get(++currentElement), viewFactory);
                    case "prev" -> formResponse(playlist.musicEntries().get(--currentElement), viewFactory);
                    default -> new HandlingState(viewFactory.getTextMessageView("Illegal argument"), false);
                };
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), false);
            }
        }
    }
}