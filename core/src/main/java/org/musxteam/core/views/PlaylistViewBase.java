package org.musxteam.core.views;

import org.musxteam.core.views.types.IView;
import org.musxteam.core.views.types.PlaylistView;

import java.util.ArrayList;

public abstract class PlaylistViewBase implements IView {
    protected final String title;
    protected final ArrayList<PlaylistView> playlistViews;

    public PlaylistViewBase(String title, ArrayList<PlaylistView> playlistViews) {
        this.title = title;
        this.playlistViews = playlistViews;
    }
}
