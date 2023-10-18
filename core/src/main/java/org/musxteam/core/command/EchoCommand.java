package org.musxteam.core.command;

import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.CommandBase;

public class EchoCommand extends CommandBase {
    @Override
    public HandlingState handleRequest(IRequest request) {
        return new HandlingState(request.getText(), true);
    }
}
