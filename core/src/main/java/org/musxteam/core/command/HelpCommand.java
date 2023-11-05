package org.musxteam.core.command;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.types.CommandBase;
import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.ICommandState;

public class HelpCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() {
        return new StartState();
    }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            return new HandlingState(RequestReplies.HELP.getReply(), true);
        }
    }
}
