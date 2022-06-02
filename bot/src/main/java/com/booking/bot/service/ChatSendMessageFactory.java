package com.booking.bot.service;

import com.booking.bot.state.Context;
import com.booking.bot.state.Stage;
import com.booking.bot.view.OrganizationDto;
import com.booking.bot.view.PersonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class ChatSendMessageFactory {
    private final MenuKeyboardFactory menuKeyboardFactory;
    private final PersonService personService;

    public SendMessage createSendMessage(Context context, Message message) {
        Long userId = message.getFrom().getId();
        String userName = message.getFrom().getUserName();
        if (context.getStage().equals(Stage.MAIN)) {
            personService.addPerson(new PersonDto(userId, userName));
            return SendMessage.builder()
                    .text(String.format("Hi,  %s! Сервис по бронированию.", userName))
                    .chatId(message.getChatId().toString())
                    .replyMarkup(menuKeyboardFactory.createMainKeyboard())
                    .build();
        }
        return SendMessage.builder()
                .text("Неизвестная команда.")
                .chatId(message.getChatId().toString())
                .build();
    }
}
