package com.booking.bot;

import com.booking.bot.service.ChatService;
import com.booking.bot.state.Command;
import com.booking.bot.state.Context;
import com.booking.bot.state.Stage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final ChatService chatService;
    private final Map<Long, Context> contextMap = new ConcurrentHashMap<>();

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (update.hasCallbackQuery()) {
            try {
                handleCallback(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
        if (!callbackQuery.getData().isEmpty()) {
            execute(
                    chatService.commandSwitch(
                            contextMap.get(callbackQuery.getMessage().getChatId()),
                            callbackQuery.getMessage()
                    )
            );
        } else {
            System.out.println("callbackQuery is empty");
        }
        System.out.println(callbackQuery.getData());
    }

    private void handleMessage(Message message) throws TelegramApiException, JsonProcessingException {
        if (message.isCommand()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals(Command.START.getValue())) {
                    //Создание контекста чата
                    contextMap.putIfAbsent(message.getFrom().getId(), new Context(message.getFrom().getId(),
                            execute(chatService.commandSwitch(command, message))
                                    .getMessageId()));
                    contextMap.get(message.getFrom().getId()).setCommand(Command.START);
                    contextMap.get(message.getFrom().getId()).setStage(Stage.MAIN);
                }
            }
        }
    }
}
