package com.booking.bot;

import com.booking.bot.dto.BookingDto;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.entity.Reservation;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import reactor.core.publisher.Mono;

import java.util.*;

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
            } catch (TelegramApiException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private final Map<Long, String> statusChat = new HashMap<>();

    private void handleMessage(Message message) throws TelegramApiException, JsonProcessingException {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/find" -> {
                        statusChat.put(message.getFrom().getId(), "/find");
                    }
                    case "/organizations" -> {
                        statusChat.put(message.getFrom().getId(), "/organizations");
                    }
                    case "/help" -> sendDefaultMessage(message);
                }
            }
        }
        chatCommunication(message);
    }

    private void chatCommunication(Message message) throws TelegramApiException {
        WebClient client = WebClient.create("http://localhost:8080");
        String messageText = message.getText();
        Optional<String> messageString = parseString(messageText);

        switch (statusChat.get(message.getFrom().getId())) {
            case "/find" -> {
                if (message.hasEntities()) {
                    execute(
                            SendMessage.builder()
                                    .text("Введите название организации:")
                                    .chatId(message.getChatId().toString())
                                    .build()
                    );
                    break;
                }
                String page = "0";
                String pageSize = "20";
                String sortBy = "rate";
                Mono<BookingDto[]> bookingDtoMono = client.get()
                        .uri("/booking?pageNo={page}&pageSize={pageSize}&sortBy={sortBy}", page, pageSize, sortBy)
                        .retrieve().bodyToMono(BookingDto[].class);
                BookingDto[] bookingDtoList = bookingDtoMono.share().block();
                StringBuilder outMessage = new StringBuilder();
                for (BookingDto bookingDto : bookingDtoList) {
                    if (bookingDto.organizationName().equals(messageString.get())) {
                        outMessage.append(
                                "Название: " + bookingDto.organizationName() + "\n" +
                                        "Средний чек: " + bookingDto.bill() + "\n" +
                                        "Рейтинг: " + bookingDto.rate() + "\n" +
                                        "Доступные бронирования:\n");
                        for (Reservation r : bookingDto.slotsOfReservation()) {
                            outMessage.append(r.beginning() + "-" + r.ending() + " cвободно " + r.numbersOfTables() + " столов" + "\n");
                        }
                        execute(
                                SendMessage.builder()
                                        .text(outMessage.toString())
                                        .chatId(message.getChatId().toString())
                                        .build()
                        );
                        break;
                    }
                }
                statusChat.put(message.getFrom().getId(), "free");
            }
            case "/organizations" -> {
                Mono<OrganizationDto[]> organizationDtoMono = client.get().uri("organization?pageNo=0&pageSize=2&sortBy=name").retrieve().bodyToMono(OrganizationDto[].class);
                OrganizationDto[] organizationDto = organizationDtoMono.share().block();
                StringBuilder outMessage = new StringBuilder();
                outMessage.append("Список организаций:\n");
                for (OrganizationDto o : organizationDto) {
                    outMessage.append(o.name()).append("\n");
                }
                execute(
                        SendMessage.builder()
                                .text(outMessage.toString())
                                .chatId(message.getChatId().toString())
                                .build()
                );
                statusChat.put(message.getFrom().getId(), "free");
            }
            case "/free" -> {
                execute(
                        SendMessage.builder()
                                .text("Выход в главное меню")
                                .chatId(message.getChatId().toString())
                                .build()
                );
            }
            default -> sendDefaultMessage(message);
        }
    }

    private void sendDefaultMessage(Message message) {
        try {
            execute(
                    SendMessage.builder()
                            .text("""
                                    Сервис по бронированию.
                                    /find - поиск бронирования.
                                    /organizations - просмотр доступных организаций.""")
                            .chatId(message.getChatId().toString())
                            .build()
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
