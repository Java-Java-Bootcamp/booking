package com.booking.bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.Optional;

public interface BookingService {
    void chooseAction(String command,Message message) throws TelegramApiException;

    String getValueFromChat(String mapValue, String messageString, Message message, Map<Long, String> statusChat);

    Optional<String> parseString(String messageText);
}
