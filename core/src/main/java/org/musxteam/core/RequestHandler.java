package org.musxteam.core;

import java.util.HashMap;

import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.CommandBase;

public class RequestHandler {
    private final HashMap<String, CommandBase> handle_users = new HashMap<>();

    public String handleRequest(IRequest request) {
        String userId = request.getUserId();

        if (handle_users.containsKey(userId)) {
            CommandBase command = handle_users.get(userId);
            HandlingState state = command.handleRequest(request);

            if (state.isHandled()) { handle_users.remove(userId);} return state.response();
        }
        return RequestReplies.EMPTY_COMMAND.getReply();
    }

    public void startNewCommand(IRequest request, CommandBase command) {
        handle_users.put(request.getUserId(), command);
    }
}
