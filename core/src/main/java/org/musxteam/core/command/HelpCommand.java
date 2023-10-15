package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.types.ICommand;
import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;

public class HelpCommand implements ICommand {
    @Override
    public HandlingState handleRequest(IRequest request) {
        return new HandlingState(RequestReplies.HELP.getReply(), true);
    }
}
