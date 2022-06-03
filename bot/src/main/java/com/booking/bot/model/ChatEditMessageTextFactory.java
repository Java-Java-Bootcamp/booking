package com.booking.bot.model;

import com.booking.bot.state.Context;
import com.booking.bot.view.OrganizationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class ChatEditMessageTextFactory {
    private final MenuKeyboardFactory menuKeyboardFactory;
    private final OrganizationService organizationService;


    public EditMessageText createEditMessageText(Context context, Message message) {
        String userName = message.getFrom().getUserName();
        switch (context.getStage()) {
            case MAIN -> {
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text(TextConstants.createWelcome(userName))
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuKeyboardFactory.createMainKeyboard())
                        .build();
            }
            case TYPE -> {
                context.setPage(0);
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text(TextConstants.CHOOSE_TYPE)
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuKeyboardFactory.createOrganizationTypeKeyboard())
                        .build();
            }
            case ORGANIZATIONS -> {
                context.setType(context.getCallbackData());
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text(TextConstants.CHOOSE_TYPE)
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuKeyboardFactory.createChoiceOrganizationKeyboard(context))
                        .build();
            }
            case PAGE -> {
                context.setPage(Integer.parseInt(context.getCallbackData()));
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text(TextConstants.CHOOSE_TYPE)
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuKeyboardFactory.createChoiceOrganizationKeyboard(context))
                        .build();
            }
            case DESCRIPTION -> {
                OrganizationDto organization = organizationService.getById(context.getCallbackData());
                return EditMessageText.builder()
                        .messageId(context.getMessageId())
                        .text(TextConstants.createDescription(organization))
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuKeyboardFactory.createDescriptionOrganizationKeyboard(context))
                        .build();
            }
            default -> {
                return null;
            }
        }
    }
}
