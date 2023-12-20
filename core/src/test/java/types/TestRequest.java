package types;

import music.TestMusicService;
import org.musxteam.core.IRequest;
import org.musxteam.database.models.MusxUser;

public class TestRequest implements IRequest {
    private final String text;
    private static final MusxUser user = new MusxUser(0, "test", new TestMusicService());

    public TestRequest(String text) { this.text = text; }

    @Override
    public String getText() { return text; }
    @Override
    public String getUserId() { return "Test user id"; }
    @Override
    public String getChatId() { return "Test chat id"; }
    @Override
    public MusxUser getUser() { return user; }
}
