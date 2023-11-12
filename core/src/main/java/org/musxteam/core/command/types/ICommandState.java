package org.musxteam.core.command.types;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.types.IViewFactory;

public interface ICommandState {
    HandlingState handleRequest(IRequest request, IViewFactory viewFactory);
}
