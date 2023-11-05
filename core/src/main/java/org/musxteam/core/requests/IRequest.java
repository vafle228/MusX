package org.musxteam.core.requests;

import org.musxteam.database.models.MusxUser;

public interface IRequest {
    String getText();
    String getUserId();
    MusxUser getUser();
}
