package org.musxteam.core.command;

import org.musxteam.core.IRequest;
import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.PlaylistViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.core.views.types.PlaylistView;
import org.musxteam.database.managers.PlayListManager;
import org.musxteam.database.models.PlaylistModel;

import java.util.ArrayList;

public class DeletePlaylistCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            ArrayList<PlaylistView> views = new ArrayList<>();

            for (PlaylistModel playlist : PlayListManager.getAllUserPlaylists(request.getUser().getId())) {
                views.add(new PlaylistView(playlist.title(), Integer.toString(playlist.id())));
            }
            PlaylistViewBase view = viewFactory.getPlaylistView(RequestReplies.PLAYLIST_CHOOSE.getReply(), views);

            changeState(new DeletePlaylistState()); return new HandlingState(view, false);
        }
    }

    class DeletePlaylistState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            try {
                int playlistId = Integer.parseInt(request.getText());
                PlayListManager.deletePlaylist(playlistId);
                return new HandlingState(viewFactory.getTextMessageView("Successful deleted"), true);
            }
            catch (IllegalArgumentException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.getMessage()), false);
            }
        }
    }
}
