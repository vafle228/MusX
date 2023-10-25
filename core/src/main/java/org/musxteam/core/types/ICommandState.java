package org.musxteam.core.types;

import org.musxteam.core.requests.IRequest;

import java.io.IOException;

public interface ICommandState {
    HandlingState handleRequest(IRequest request);
}
