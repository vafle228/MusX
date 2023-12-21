package org.musxteam.core.views;

import org.musxteam.core.views.types.IView;

public abstract class PlaylistTrackViewBase implements IView {
    protected final String title;
    protected final String videoId;
    protected final String entryId;
    protected final String channelTitle;
    protected final String thumbnailUrl;

    public PlaylistTrackViewBase(String title, String videoId, String entryId, String channelTitle, String thumbnailUrl) {
        this.title = title;
        this.videoId = videoId;
        this.entryId = entryId;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
    }
}
