package org.musxteam.core.views.types;

import org.musxteam.core.views.DownloadCommandViewBase;
import org.musxteam.core.views.HelpCommandViewBase;
import org.musxteam.core.views.SearchCommandViewBase;

public interface ICommandViewFactory {
    HelpCommandViewBase getHelpCommandView();
    SearchCommandViewBase getSearchCommandView();
    DownloadCommandViewBase getDownloadCommandView();
}
