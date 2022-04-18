package com.booking.bot;

import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
@NoArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final Map<Long, String> statusChat = new HashMap<>();

    private BookingService bookingService;

    @Autowired
    public TelegramBot(BookingService bookingService) {
        this.bookingService = bookingService;
    }

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
        //place for buttons. Not ready
        String param = callbackQuery.getData();
        System.out.println(param);
        executeString(bookingService
                .getValueFromChat(statusChat.get(callbackQuery.getMessage().getFrom().getId()),
                        param, callbackQuery.getMessage(), statusChat), callbackQuery.getMessage());
    }

    private void handleMessage(Message message) throws TelegramApiException, JsonProcessingException {

        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                String s = bookingService.chooseCommand(command, statusChat, message);
                List<InlineKeyboardButton> buttons = new ArrayList<>();
                InlineKeyboardButton inlineKeyboardButton = InlineKeyboardButton.builder()
                        .text("rate")
                        .callbackData("rate")
                        .build();
                InlineKeyboardButton inlineKeyboardButton1 = InlineKeyboardButton.builder()
                        .text("bill")
                        .callbackData("bill")
                        .build();
                buttons.add(inlineKeyboardButton);
                buttons.add(inlineKeyboardButton1);
                SendMessage sendMessage = SendMessage.builder()
                        .text("Please choose product category")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(buttons).build())
                        .build();
                execute(sendMessage);
                executeString(s, message);
            }
            return;
        }

        if (message.hasText()) {
            Optional<String> messageString = bookingService.parseString(message.getText());
            String mapValue = statusChat.get(message.getFrom().getId());
            if (messageString.isPresent()) {
                executeString(bookingService.getValueFromChat(mapValue, messageString.get(), message, statusChat), message);
            }
        }
    }

    private void executeString(String executeStr, Message message) throws TelegramApiException {
        execute(
                SendMessage.builder()
                        .text(executeStr)
                        .chatId(message.getChatId().toString())
                        .build()
        );
    }
}
