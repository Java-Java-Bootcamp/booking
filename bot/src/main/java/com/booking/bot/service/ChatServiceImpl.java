package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final MenuService menuService;
    private final BotAdapter botAdapter;

    private final Map<Long, String> chatState = new HashMap<>();
    private final Map<Long, String> chatData = new HashMap<>();

    public SendMessage commandSwitch(Long userId, String command, Message message) {
        String userName = message.getFrom().getUserName();
        if ("/start".equals(command)) {
            botAdapter.addPerson(new PersonDto(userId, userName));
            chatState.put(userId, "main menu");
            return SendMessage.builder()
                    .text("Hi, " + userName + "! Сервис по бронированию.")
                    .chatId(message.getChatId().toString())
                    .replyMarkup(menuService.getKeyboard(chatState, message, command))
                    .build();
        }
        return SendMessage.builder()
                .text("Я не понимаю что ты хочешь от меня")
                .chatId(message.getChatId().toString())
                .build();
    }

    public EditMessageText commandSwitch(Long userId, String command, Message message, Integer lastMessageId) {
        String userName = message.getFrom().getUserName();
        switch (command) {
            case "/start" -> {
                chatState.put(userId, "main menu");
                return EditMessageText.builder()
                        .messageId(lastMessageId)
                        .text("Hi, " + userName + "! Сервис по бронированию.")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message, command))
                        .build();
            }
            case "/find" -> {
                chatState.put(userId, "choice of organization type");
                return EditMessageText.builder()
                        .messageId(lastMessageId)
                        .text("Выбери тип организации:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message, command))
                        .build();
            }
            case "back from description" -> {
                chatState.put(userId, "choice of organizations");
                return EditMessageText.builder()
                        .messageId(lastMessageId)
                        .text("Выбери организацию:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message, chatData.get(userId)))
                        .build();
            }
            default -> {
                if ("choice of organizations".equals(chatState.get(userId))) {
                    return EditMessageText.builder()
                            .messageId(lastMessageId)
                            .text("Выбери организацию:")
                            .chatId(message.getChatId().toString())
                            .replyMarkup(menuService.getKeyboard(chatState, message, command))
                            .build();
                }
                if ("description of the organization".equals(chatState.get(userId))) {
                    OrganizationDto organization = botAdapter.getOrganizationById(command);
                    chatData.put(userId, organization.typeOrganization());
                    return EditMessageText.builder()
                            .messageId(lastMessageId)
                            .text(organization.name() + ":" +
                                    "\nРейтинг = " + organization.rating() +
                                    "\nСредний чек = " + organization.averageCheck() +
                                    "\nРасписание = " + organization.schedule())
                            .chatId(message.getChatId().toString())
                            .replyMarkup(menuService.getKeyboard(chatState, message, command))
                            .build();
                }
                return null;
            }
        }
    }
}
