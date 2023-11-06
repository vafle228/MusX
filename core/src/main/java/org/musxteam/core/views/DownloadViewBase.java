package org.musxteam.core.views;

import org.musxteam.core.views.types.IView;

public abstract class DownloadViewBase implements IView {
    protected final String title;
    protected final String audioUrl;
    protected final String thumbnailUrl;

    public DownloadViewBase(String title, String audioUrl, String thumbnailUrl) {
        this.title = title;
        this.audioUrl = audioUrl;
        this.thumbnailUrl = thumbnailUrl;
    }
}
