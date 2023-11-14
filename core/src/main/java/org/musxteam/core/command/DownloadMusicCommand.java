package org.musxteam.core.command;

import org.musxteam.core.IRequest;
import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.TextMessageViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.service.MusicServiceBase;

import java.io.IOException;

public class DownloadMusicCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() { return new StartState(); }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            TextMessageViewBase view = viewFactory.getTextMessageView(
                    RequestReplies.DOWNLOAD_START.getReply()
            );
            changeState(new DownloadState()); return new HandlingState(view, false);
        }
    }

    class DownloadState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
            try {
                MusicServiceBase service = request.getUser().musicService;
                MusicInstance instance = service.downloadMusic(request.getText());

                DownloadViewBase view = viewFactory.getDownloadView(
                        instance.title(), instance.musicUrl(), instance.thumbnailUrl()
                ); return new HandlingState(view, true);
            } catch (IOException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), true);
            }
        }
    }
}
