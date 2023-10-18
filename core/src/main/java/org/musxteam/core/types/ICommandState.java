package org.musxteam.core.types;

import org.musxteam.core.requests.IRequest;

public interface ICommandState {
    HandlingState handleRequest(IRequest request);
}
