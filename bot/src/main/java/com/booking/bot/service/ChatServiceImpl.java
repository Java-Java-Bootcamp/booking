package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    @Autowired
    private MenuService menuService;

    @Autowired
    private BotAdapter botAdapter;

    public ChatServiceImpl(BotAdapter botAdapter, MenuService menuService) {
        this.botAdapter = botAdapter;
        this.menuService = menuService;
    }

    Map<Long, String> chatState = new HashMap<>();

    public SendMessage commandSwitch(Long userId, String command, Message message) {
        String userName = message.getFrom().getUserName();
        switch (command) {
            case "/start" -> {
                botAdapter.addPerson(new PersonDto(userId, userName), "/person");
                chatState.put(userId, "/start");
                return SendMessage.builder()
                        .text("Hi, " + userName + "! Сервис по бронированию.")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message,command))
                        .build();
            }
            case "/find" -> {
                chatState.put(userId, "/types");
                return SendMessage.builder()
                        .text("Выбери тип организации:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message,command))
                        .build();
            }
            default -> {
                return SendMessage.builder()
                        .text("Я не понимаю что ты хочешь от меня")
                        .chatId(message.getChatId().toString())
                        .build();
            }
        }
    }

    public EditMessageText commandSwitch(Long userId, String command, Message message, Integer lastMessageId) {
        String userName = message.getFrom().getUserName();
        switch (command) {
            case "/start" -> {
                chatState.put(userId, "/start");
                return EditMessageText.builder()
                        .messageId(lastMessageId)
                        .text("Hi, " + userName + "! Сервис по бронированию.")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message,command))
                        .build();
            }
            case "/find" -> {
                chatState.put(userId, "start > types");
                return EditMessageText.builder()
                        .messageId(lastMessageId)
                        .text("Выбери тип организации:")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(menuService.getKeyboard(chatState, message,command))
                        .build();
            }
            default -> {
                if ("types > organizations".equals(chatState.get(userId))) {
                    return EditMessageText.builder()
                            .messageId(lastMessageId)
                            .text("Выбери организацию:")
                            .chatId(message.getChatId().toString())
                            .replyMarkup(menuService.getKeyboard(chatState, message,command))
                            .build();
                }
                return null;
            }
        }
    }
}
