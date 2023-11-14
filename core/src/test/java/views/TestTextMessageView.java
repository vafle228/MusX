package views;

import org.musxteam.core.IRequest;
import org.musxteam.core.views.TextMessageViewBase;

public class TestTextMessageView extends TextMessageViewBase {
    public TestTextMessageView(String text) { super(text); }

    @Override
    public void render(IRequest request) { System.out.println(text); }
}
