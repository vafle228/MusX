package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.CommandBase;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.ICommandState;
import org.musxteam.music.download.YoutubeDownloadService;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.search.YoutubeSearchService;
import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;
import org.musxteam.music.service.MusicServiceBase;
import org.musxteam.music.service.YoutubeMusicService;

import java.io.IOException;

public class DownloadMusicCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            changeState(new DownloadState());
            return new HandlingState(RequestReplies.DOWNLOAD_START.getReply(), false);
        }
    }

    class DownloadState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            try {
                MusicServiceBase service = request.getUser().musicService;
                MusicInstance instance = service.downloadMusic(request.getText());
                return new HandlingState(instance.musicUrl(), true);
            }
            catch (IOException e) { return new HandlingState(e.getMessage(), true); }
        }
    }
}
