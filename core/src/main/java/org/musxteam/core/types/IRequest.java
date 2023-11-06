package org.musxteam.core.types;

import org.musxteam.database.models.MusxUser;

public interface IRequest {
    String getText();
    String getUserId();
    MusxUser getUser();
}
