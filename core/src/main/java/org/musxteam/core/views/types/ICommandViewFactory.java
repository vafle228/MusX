package org.musxteam.core.views.types;

public interface ICommandViewFactory {
    ICommandView getHelpCommandView();
    ICommandView getSearchCommandView();
    ICommandView getDownloadCommandView();
}
