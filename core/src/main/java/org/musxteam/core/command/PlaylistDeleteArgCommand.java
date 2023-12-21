package org.musxteam.core.command;

import org.musxteam.core.IRequest;
import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.database.managers.PlayListManager;

public class PlaylistDeleteArgCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {

            try {
                String[] ids = request.getText().split(" ");

                int entryId = Integer.parseInt(ids[2]);
                int playlistId = Integer.parseInt(ids[1]);

                PlayListManager.deleteMusicEntry(playlistId, entryId);
                return new HandlingState(viewFactory.getTextMessageView(
                        RequestReplies.PLAYLIST_TRACK_REMOVED.getReply()), true);
            }
            catch (IllegalArgumentException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.getMessage()), true);
            }
        }
    }
}
