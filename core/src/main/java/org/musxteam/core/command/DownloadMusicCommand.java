package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.IRequest;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.service.MusicServiceBase;

import java.io.IOException;

public class DownloadMusicCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            changeState(new DownloadState());
            return new HandlingState(null, false);
        }
    }

    class DownloadState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            try {
                MusicServiceBase service = request.getUser().musicService;
                MusicInstance instance = service.downloadMusic(request.getText());
                return new HandlingState(null, true);
            }
            catch (IOException e) { return new HandlingState(null, true); }
        }
    }
}
