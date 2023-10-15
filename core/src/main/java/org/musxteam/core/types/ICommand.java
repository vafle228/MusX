package org.musxteam.core.types;

import org.musxteam.core.requests.IRequest;

public interface ICommand {
    HandlingState handleRequest(IRequest request);
}
