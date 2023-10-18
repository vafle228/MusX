package org.musxteam.core.types;

import org.musxteam.core.requests.IRequest;

public abstract class CommandBase {
    ICommandState state;

    protected void changeState(ICommandState state) {
        this.state = state;
    }
    public HandlingState handleRequest(IRequest request) {
        return state.handleRequest(request);
    }
}
