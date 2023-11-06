package org.musxteam.core.views;

import org.musxteam.core.views.types.IView;

public abstract class TextMessageViewBase implements IView {
    protected final String text;
    public TextMessageViewBase(String text) { this.text = text; }
}
