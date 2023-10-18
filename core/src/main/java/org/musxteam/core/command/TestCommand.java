package org.musxteam.core.command;

import org.musxteam.core.requests.IRequest;
import org.musxteam.core.types.HandlingState;
import org.musxteam.core.types.CommandBase;
import org.musxteam.core.types.ICommandState;

public class TestCommand extends CommandBase {
    private String test = "123";

    public TestCommand() {
        this.changeState(new TestState());
    }

    class TestState implements ICommandState {
        @Override
        public HandlingState handleRequest(IRequest request) {
            if (test1()) {
                return new HandlingState(test, true);
            }
            return new HandlingState(test, false);
        }

        public boolean test1() { return true; }
    }
}
