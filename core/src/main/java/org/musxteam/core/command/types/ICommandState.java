package org.musxteam.core.command.types;

import org.musxteam.core.IRequest;

public interface ICommandState {
    HandlingState handleRequest(IRequest request);
}
