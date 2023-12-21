package org.musxteam.core.command;

import org.musxteam.core.IRequest;
import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.database.managers.PlayListManager;

public class AddPlaylistCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            TextMessageViewBase view = viewFactory.getTextMessageView(
                    RequestReplies.ADD_PLAYLIST_START.getReply()
            );
            changeState(new PlaylistAddState()); return new HandlingState(view, false);
        }
    }

    class PlaylistAddState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            PlayListManager.addPlayList(request.getText(), request.getUser().getId());
            return new HandlingState(viewFactory.getTextMessageView(RequestReplies.PLAYLIST_ADDED.getReply()), true);
        }
    }
}
