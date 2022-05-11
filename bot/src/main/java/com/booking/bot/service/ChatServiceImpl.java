package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import com.booking.bot.state.Context;
import com.booking.bot.state.Stage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final MenuService menuService;
    private final BotAdapter botAdapter;

    public SendMessage sendMessage(Context context, Message message) throws JsonProcessingException {
        Long userId = message.getFrom().getId();
        String userName = message.getFrom().getUserName();
        if (context.getStage().equals(Stage.MAIN)) {
            botAdapter.addPerson(new PersonDto(userId, userName));
            return SendMessage.builder()
                    .text("Hi, " + userName + "! Сервис по бронированию.")
                    .chatId(message.getChatId().toString())
                    .replyMarkup(menuService.getMainKeyboard(context))
                    .build();
        }
        return SendMessage.builder()
                .text("Я не понимаю что ты хочешь от меня")
                .chatId(message.getChatId().toString())
                .build();
    }

    public EditMessageText editMessageText(Context context, Message message) throws JsonProcessingException {
        String userName = message.getFrom().getUserName();
        switch (context.getStage()) {
            case MAIN -> {
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text("Hi, " + userName + "! Сервис по бронированию.")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getMainKeyboard(context))
                        .build();
            }
            case TYPE -> {
                context.setPage(0);
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text("Выбери тип организации:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getOrganizationTypeKeyboard(context))
                        .build();
            }
            case ORGANIZATIONS -> {
                context.setType(context.getCallbackData());
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text("Выбери организацию:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getChoiceOrganizationKeyboard(context))
                        .build();
            }
            case PAGE -> {
                context.setPage(Integer.parseInt(context.getCallbackData()));
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text("Выбери организацию:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getChoiceOrganizationKeyboard(context))
                        .build();
            }
            case DESCRIPTION -> {
                OrganizationDto organization = botAdapter.getOrganizationById(context.getCallbackData());
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text(organization.name() + ":" +
                                "\nРейтинг = " + organization.rating() +
                                "\nСредний чек = " + organization.averageCheck() +
                                "\nРасписание = " + organization.schedule())
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getDescriptionOrganizationKeyboard(context))
                        .build();
            }
            default -> {
                return null;
            }
        }
    }
}
