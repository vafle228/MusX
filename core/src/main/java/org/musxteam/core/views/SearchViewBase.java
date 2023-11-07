package org.musxteam.core.views;

import org.musxteam.core.views.types.IView;

import java.text.MessageFormat;

public abstract class SearchViewBase implements IView {
    protected final String title;
    protected final String videoId;
    protected final String channelTitle;
    protected final String thumbnailUrl;

    public SearchViewBase(String title, String videoId, String channelTitle, String thumbnailUrl) {
        this.title = title;
        this.videoId = videoId;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
    }
}
