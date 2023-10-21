package org.musxteam.core.command;

import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.CommandBase;
import org.musxteam.core.types.ICommandState;

public class EchoCommand extends CommandBase {
    @Override
    protected ICommandState initStartState() {
        return new StartState();
    }

    class StartState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            changeState(new EchoState());
            return new HandlingState("Write a message", false);
        }
    }
    static class EchoState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            return new HandlingState(request.getText(), true);
        }
    }
}
