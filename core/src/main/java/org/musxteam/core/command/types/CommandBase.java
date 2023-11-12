package org.musxteam.core.command.types;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.types.IViewFactory;

public abstract class CommandBase {
    protected final IViewFactory viewFactory;
    private ICommandState state = initStartState();

    public CommandBase(IViewFactory viewFactory) { this.viewFactory = viewFactory; }

    protected abstract ICommandState initStartState();
    protected void changeState(ICommandState state) {
        this.state = state;
    }
    public HandlingState handleRequest(IRequest request) {
        return state.handleRequest(request);
    }
}
