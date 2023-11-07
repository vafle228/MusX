package org.musxteam.telegram.requests;

import org.musxteam.core.IRequest;
import org.musxteam.database.models.MusxUser;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramRequest implements IRequest {
    private final Message message;

    public TelegramRequest(Message message){
        this.message = message;
    }

    @Override
    public String getText() {
        return message.getText();
    }
    @Override
    public String getUserId() {
        return message.getFrom().getId().toString();
    }
    @Override
    public String getChatId() {
        return message.getChatId().toString();
    }
    @Override
    public MusxUser getUser() {
        return MusxUser.getInstance();
    }
}
