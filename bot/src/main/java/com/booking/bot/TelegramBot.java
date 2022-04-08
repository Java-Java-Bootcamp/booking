package com.booking.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Flux;

import java.util.List;
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

    private boolean inSearch;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String chatId = update.getMessage().getChatId().toString();

            SendMessage sm = new SendMessage();
            sm.setChatId(chatId);
            WebClient client = WebClient.create("http://localhost:8080");
            ObjectMapper mapper = new ObjectMapper();

            if (message.equals("/find") || inSearch) {
                if (inSearch) {

                    String getJSON = client.get().uri("/booking?nameOfOrganization={message}", message).retrieve().bodyToMono(String.class).block();
                    getJSON = getJSON.substring(1);
                    getJSON = getJSON.substring(0, getJSON.length() - 1);
                    try {
                        sm.setText(mapper.readTree(getJSON).get("id").toString());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    inSearch = false;
                } else {
                    inSearch = true;
                    sm.setText("Введите имя организации");
                }
            } else if (message.equals("/organizations")) {
                String getJSON = client.get().uri("bookings?limit={page}&offset={size}", "0", "30").retrieve().bodyToMono(String.class).block();
                getJSON = getJSON.substring(1);
                getJSON = getJSON.substring(0, getJSON.length() - 1);
//                    sm.setText(mapper.readTree(getJSON).get("id").toString());
                System.out.println(getJSON);
                sm.setText(getJSON);
            } else {
                sm.setText("Сервис по бронированию.\n" +
                        "/find - поиск бронирования.\n" +
                        "/organizations - просмотр доступных организаций.");
            }
            send(sm);
        }
    }


    private String getBooking(String uri, String param) {
        WebClient client = WebClient.create("http://localhost:8080");
        return client.get().uri(uri, param).retrieve().bodyToMono(String.class).block();
    }

    private void send(SendMessage sm) {
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
