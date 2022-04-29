package com.booking.bot.service;

import com.booking.bot.state.Context;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ChatService {
    SendMessage commandSwitch(String command, Message message);

    EditMessageText commandSwitch(Context context, Message message);
}
