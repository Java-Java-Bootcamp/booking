package com.booking.bot;

import com.booking.bot.dto.BookingDto;
import com.booking.bot.dto.OrganizationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TelegramBot extends TelegramLongPollingBot {

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

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<Long, String> statusChat = new HashMap<>();

    private void handleMessage(Message message) throws TelegramApiException, JsonProcessingException {

        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/find":
                        statusChat.put(message.getFrom().getId(), "/find");
                        execute(
                                SendMessage.builder()
                                        .text("Введите имя организации")
                                        .chatId(message.getChatId().toString())
                                        .build()
                        );
                        break;
                    case "/organizations":
                        statusChat.put(message.getFrom().getId(), "/organizations");
                        execute(
                                SendMessage.builder()
                                        .text("Список организаций:")
                                        .chatId(message.getChatId().toString())
                                        .build()
                        );
                        break;
                    default:
                        execute(
                                SendMessage.builder()
                                        .text("Сервис по бронированию.\n" +
                                                "/find - поиск бронирования.\n" +
                                                "/organizations - просмотр доступных организаций.")
                                        .chatId(message.getChatId().toString())
                                        .build()
                        );
                        break;
                }
            }
        }

        WebClient client = WebClient.create("http://localhost:8080");
        String messageText = message.getText();
        Optional<String> messageString = parseString(messageText);
        if (statusChat.get(message.getFrom().getId()) != null && statusChat.get(message.getFrom().getId()).equals("/find")) {
            Mono<OrganizationDto[]> organizationDtoMono = client.get().uri("/organization?name={name}", messageString.get()).retrieve().bodyToMono(OrganizationDto[].class);
            OrganizationDto[] organizationDto = organizationDtoMono.share().block();
            OrganizationDto org = organizationDto[0];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Название: " + org.name() + "\n" +
                    "Расписание: " + org.schedule() + "\n" +
                    "Кол-во столов: " + org.numbersOfTables() + "\n" +
                    "Средний чек: " + org.averageCheck() + "\n" +
                    "Рейтинг: " + org.rating() + "\n");
            execute(
                    SendMessage.builder()
                            .text(stringBuilder.toString())
                            .chatId(message.getChatId().toString())
                            .build()
            );
            statusChat.put(message.getFrom().getId(), "free");
        } else if (statusChat.get(message.getFrom().getId()).equals("/organizations")) {
            Mono<OrganizationDto[]> organizationDtoMono = client.get().uri("organization?pageNo=0&pageSize=2&sortBy=name").retrieve().bodyToMono(OrganizationDto[].class);
            OrganizationDto[] organizationDto = organizationDtoMono.share().block();
            System.out.println(33);
            StringBuilder stringBuilder = new StringBuilder();
            for (OrganizationDto o : organizationDto) {
                stringBuilder.append(o.name() + "\n");
            }
            execute(
                    SendMessage.builder()
                            .text(stringBuilder.toString())
                            .chatId(message.getChatId().toString())
                            .build()
            );
            statusChat.put(message.getFrom().getId(), "free");
        } else {
            execute(
                    SendMessage.builder()
                            .text("Сервис по бронированию.\n" +
                                    "/find - поиск бронирования.\n" +
                                    "/organizations - просмотр доступных организаций.")
                            .chatId(message.getChatId().toString())
                            .build()
            );
        }

    }

    private Optional<Double> parseDouble(String messageText) {
        try {
            return Optional.of(Double.parseDouble(messageText));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> parseString(String messageText) {
        try {
            return Optional.of(String.valueOf(messageText));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
