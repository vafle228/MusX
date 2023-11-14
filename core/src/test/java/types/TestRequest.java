package types;

import org.musxteam.core.IRequest;
import org.musxteam.database.models.MusxUser;

public class TestRequest implements IRequest {
    @Override
    public String getText() { return "Test text"; }
    @Override
    public String getUserId() { return "Test user id"; }
    @Override
    public String getChatId() { return "Test chat id"; }
    @Override
    public MusxUser getUser() { return MusxUser.getInstance(); }
}
