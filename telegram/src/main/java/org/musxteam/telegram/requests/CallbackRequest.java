package org.musxteam.telegram.requests;

import org.musxteam.core.IRequest;
import org.musxteam.database.models.MusxUser;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class CallbackRequest implements IRequest {
    private final CallbackQuery callbackQuery;
    public CallbackRequest(CallbackQuery callbackQuery) { this.callbackQuery = callbackQuery; }

    @Override
    public String getText() { return callbackQuery.getData(); }
    @Override
    public String getUserId() { return callbackQuery.getFrom().getId().toString(); }
    @Override
    public String getChatId() { return callbackQuery.getMessage().getChatId().toString(); }
    @Override
    public MusxUser getUser() { return MusxUser.getInstance(); }
}
