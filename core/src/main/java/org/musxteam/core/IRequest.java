package org.musxteam.core;

import org.musxteam.database.models.MusxUser;

public interface IRequest {
    String getText();
    String getUserId();
    String getChatId();
    MusxUser getUser();
}
