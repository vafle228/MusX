package org.musxteam.core.types;

public interface ICommandState {
    HandlingState handleRequest(IRequest request);
}
