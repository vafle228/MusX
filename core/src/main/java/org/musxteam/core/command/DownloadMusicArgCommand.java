package org.musxteam.core.command;

import org.musxteam.core.IRequest;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.ICommandState;
import org.musxteam.core.views.DownloadViewBase;
import org.musxteam.core.views.types.IViewFactory;
import org.musxteam.music.download.types.MusicInstance;
import org.musxteam.music.service.MusicServiceBase;

import java.io.IOException;

public class DownloadMusicArgCommand extends CommandBase {
    public DownloadMusicArgCommand(IViewFactory viewFactory) { super(viewFactory); }

    @Override
    protected ICommandState initStartState() { return new DownloadState(); }

    class DownloadState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            try {
                MusicServiceBase service = request.getUser().musicService;
                String videoId = request.getText().split(" ")[1];
                MusicInstance instance = service.downloadMusic(videoId);

                DownloadViewBase view = viewFactory.getDownloadView(
                        instance.title(), instance.musicUrl(), instance.thumbnailUrl()
                ); return new HandlingState(view, true);
            }
            catch (IOException | ArrayIndexOutOfBoundsException ex) {
                return new HandlingState(viewFactory.getTextMessageView(ex.toString()), true);
            }
        }
    }
}
