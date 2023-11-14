package org.musxteam.core;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

import org.musxteam.core.command.DownloadMusicArgCommand;
import org.musxteam.core.command.DownloadMusicCommand;
import org.musxteam.core.command.HelpCommand;
import org.musxteam.core.command.SearchMusicCommand;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.views.types.IView;
import org.musxteam.core.views.types.IViewFactory;

public class RequestHandler {
    private final HashMap<String, CommandBase> handleUsers = new HashMap<>();

    public IView handleRequest(IRequest request, IViewFactory viewFactory) {
        String userId = request.getUserId();

        if (handleUsers.containsKey(userId)) {
            CommandBase command = handleUsers.get(userId);
            HandlingState state = command.handleRequest(request, viewFactory);

            if (state.isHandled()) { handleUsers.remove(userId); } return state.response();
        }
        return viewFactory.getTextMessageView(RequestReplies.EMPTY_COMMAND.getReply());
    }

    public void startNewCommand(IRequest request) {
        if (Objects.equals(request.getText(), "/help"))
            handleUsers.put(request.getUserId(), new HelpCommand());

        if (Objects.equals(request.getText(), "/search"))
            handleUsers.put(request.getUserId(), new SearchMusicCommand());

        if (Objects.equals(request.getText(), "/download"))
            handleUsers.put(request.getUserId(), new DownloadMusicCommand());
    }

    public void startNewArgCommand(IRequest request) {
        if (Pattern.matches("/download .{11}", request.getText()))
            handleUsers.put(request.getUserId(), new DownloadMusicArgCommand());
    }
}
