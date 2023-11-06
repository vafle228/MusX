package org.musxteam.core;

import java.util.HashMap;
import java.util.Objects;

import org.musxteam.core.command.DownloadMusicCommand;
import org.musxteam.core.command.HelpCommand;
import org.musxteam.core.command.SearchMusicCommand;
import org.musxteam.core.command.types.HandlingState;
import org.musxteam.core.command.types.CommandBase;
import org.musxteam.core.views.types.ICommandView;
import org.musxteam.core.views.types.ICommandViewFactory;

public class RequestHandler {
    private final HashMap<String, CommandBase> handle_users = new HashMap<>();

    public ICommandView handleRequest(IRequest request) {
        String userId = request.getUserId();

        if (handle_users.containsKey(userId)) {
            CommandBase command = handle_users.get(userId);
            HandlingState state = command.handleRequest(request);

            if (state.isHandled()) { handle_users.remove(userId);} return state.response();
        }
        return null;
    }

    public void startNewCommand(IRequest request, ICommandViewFactory viewFactory) {
        if (Objects.equals(request.getText(), "/help"))
            handle_users.put(request.getUserId(), new HelpCommand(viewFactory.getHelpCommandView()));

        if (Objects.equals(request.getText(), "/search"))
            handle_users.put(request.getUserId(), new SearchMusicCommand());

        if (Objects.equals(request.getText(), "/download"))
            handle_users.put(request.getUserId(), new DownloadMusicCommand());
    }
}
