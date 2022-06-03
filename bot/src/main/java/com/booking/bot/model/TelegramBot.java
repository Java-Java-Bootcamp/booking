package com.booking.bot.model;

import com.booking.bot.state.Command;
import com.booking.bot.state.Context;
import com.booking.bot.state.Stage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final ChatEditMessageTextFactory chatEditMessageTextFactory;
    private final ChatSendMessageFactory chatSendMessageFactory;
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
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Context getContext(Message message) {
        return contextMap.get(message.getChatId());
    }

    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException, JsonProcessingException {

        Context context = getContext(callbackQuery.getMessage());
        if (!callbackQuery.getData().isEmpty()) {
            context.setCallbackData(callbackQuery.getData().split(":")[1]);
            if (Enums.getIfPresent(Stage.class, callbackQuery.getData().split(":")[0]).isPresent()) {
                context.setStage(Stage.valueOf(callbackQuery.getData().split(":")[0]));
            }
            execute(
                    chatEditMessageTextFactory.createEditMessageText(context, callbackQuery.getMessage())
            );
        } else {
            System.out.println("callbackQuery is empty");
        }
    }

    private void handleMessage(Message message) throws TelegramApiException, JsonProcessingException {
        contextMap.putIfAbsent(message.getFrom().getId(), new Context(message.getFrom().getId()));

        Context context = getContext(message);

        if (message.isCommand()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals(Command.START.getValue())) {
                    context.setStage(Stage.MAIN);
                    context.setMessageId(
                            execute(chatSendMessageFactory.createSendMessage(contextMap
                                    .get(message.getFrom().getId()), message))
                                    .getMessageId());
                }
            }
        }
    }
}
