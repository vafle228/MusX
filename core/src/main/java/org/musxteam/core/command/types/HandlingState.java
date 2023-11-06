package org.musxteam.core.command.types;

import org.musxteam.core.views.types.ICommandView;

public record HandlingState(
        ICommandView response,
        boolean isHandled
) { }
