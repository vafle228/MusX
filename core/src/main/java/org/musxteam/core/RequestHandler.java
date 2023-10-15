package org.musxteam.core;

import java.util.HashMap;

import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.ICommand;

public class RequestHandler {
    private final HashMap<String, ICommand> handle_users = new HashMap<>();

    public String handleRequest(IRequest request) {
        String userId = request.getUserId();
        ICommand command = handle_users.get(userId);
        HandlingState state = command.handleRequest(request);

        if (state.isHandled()) {
            handle_users.remove(userId);
        }
        return state.response();
    }

    public void startNewCommand(IRequest request, ICommand command) {
        handle_users.put(request.getUserId(), command);
    }
}
