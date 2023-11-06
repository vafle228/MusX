package org.musxteam.core.command.types;

import org.musxteam.core.views.types.IView;

public record HandlingState(
        IView response,
        boolean isHandled
) { }
