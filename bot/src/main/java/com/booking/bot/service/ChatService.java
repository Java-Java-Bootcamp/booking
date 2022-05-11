package com.booking.bot.service;

import com.booking.bot.state.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ChatService {
    SendMessage sendMessage(Context context, Message message) throws JsonProcessingException;

    EditMessageText editMessageText(Context context, Message message) throws JsonProcessingException;
}
