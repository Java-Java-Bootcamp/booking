package com.booking.bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.Optional;

public interface BookingService {

    String chooseCommand(String commandName, Map<Long, String> mapState, Message message) throws TelegramApiException;

    String getValueFromChat(String mapValue, String messageString, Message message, Map<Long, String> statusChat);

//    String checkingTheExistenceOfPerson(Message message);

    Optional<String> parseString(String messageText);


}
