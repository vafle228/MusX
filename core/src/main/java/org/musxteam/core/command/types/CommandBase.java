package org.musxteam.core.command.types;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.types.IViewFactory;

public abstract class CommandBase {
    private ICommandState state = initStartState();

    protected abstract ICommandState initStartState();
    protected void changeState(ICommandState state) {
        this.state = state;
    }
    public HandlingState handleRequest(IRequest request, IViewFactory viewFactory) {
        return state.handleRequest(request, viewFactory);
    }
}
