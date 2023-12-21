package org.musxteam.core.views;

import org.musxteam.core.views.types.IView;
import org.musxteam.core.views.types.PlaylistView;

import java.util.ArrayList;

public abstract class PlaylistViewBase implements IView {
    protected final ArrayList<PlaylistView> playlistViews;

    public PlaylistViewBase(ArrayList<PlaylistView> playlistViews) {
        this.playlistViews = playlistViews;
    }
}
