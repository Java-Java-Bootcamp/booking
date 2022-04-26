package com.booking.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ChatService {
    SendMessage commandSwitch(Long userId, String command, Message message);
    EditMessageText commandSwitch(Long userId, String command, Message message,Integer lastMessageId);
}
