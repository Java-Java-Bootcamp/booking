package com.booking.bot.service;

import com.booking.bot.adapter.BookingAdapter;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

@Service
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingAdapter bookingAdapter;

    @Autowired
    public BookingServiceImpl(BookingAdapter bookingAdapter) {
        this.bookingAdapter = bookingAdapter;
    }

    @Override
    public String chooseCommand(String commandName, Map<Long, String> statusChat, Message message) {

        statusChat.put(message.getFrom().getId(), commandName);
        switch (commandName) {
            case "/start" -> {
                bookingAdapter.addPerson(new PersonDto(message.getFrom().getId(), message.getFrom().getUserName()), "/person");
                return "Hi " + message.getFrom().getUserName() + "! Сервис по бронированию.\n" +
                        "/find - поиск бронирования.\n" +
                        "/organization - просмотр доступных организаций.";
            }
            case "/sign_up" -> {
                return "Type your name, please: ";
            }
            case "/find" -> {
                return "Type name of organization: ";
            }
            case "/organization" -> {
                return "Отфильтровать по: rate, bill ";
            }
            default -> {
                return "Command not found";
            }
        }
    }

    @Override
    public Optional<String> parseString(String messageText) {
        try {
            return Optional.of(String.valueOf(messageText));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public String getValueFromChat(String mapValue, String messageString,
                                   Message message, Map<Long, String> statusChat) {
        switch (mapValue) {
            case "/find" -> {
                List<OrganizationDto> organizations
                        = bookingAdapter.getOrganization("/organization?name={name}", messageString);
                System.out.println(organizations);
                return getConclusion(message, statusChat, organizations);
            }

            case "/organization" -> {
                List<OrganizationDto> organizations
                        = bookingAdapter.getOrganizations("/organization?pageNo=0&pageSize=10&sortBy={rate}", messageString);
                    return getConclusion(message, statusChat, organizations);
                }
            default -> {
                System.out.println(mapValue);
                System.out.println(messageString);
                System.out.println("def");
                return "Organization not found! Try one more time: /find";
            }
        }
    }

    private String getConclusion(Message message, Map<Long, String> statusChat, List<OrganizationDto> organizations) {
        if (organizations.isEmpty()) {
            System.out.println("empty");
            return "Organization not found! Try one more time: /find";
        }
        StringBuilder stringMessage = new StringBuilder();
        statusChat.put(message.getFrom().getId(), "free");
        for (OrganizationDto org : organizations) {
            stringMessage.append("Название: ").append(org.name()).append("\n").append("Расписание: ")
                    .append(org.schedule()).append("\n").append("Средний чек: ")
                    .append(org.averageCheck()).append("\n").append("Рейтинг: ")
                    .append(org.rating()).append("\n").append("\n");
        }
        return stringMessage.toString();
    }
}
