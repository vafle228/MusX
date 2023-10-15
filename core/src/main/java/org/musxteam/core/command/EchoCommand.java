package org.musxteam.core.command;

import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.ICommand;

public class EchoCommand implements ICommand {
    @Override
    public HandlingState handleRequest(IRequest request) {
        return new HandlingState(request.getText(), true);
    }
}
