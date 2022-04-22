package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BotAdapter botAdapter;

    public BookingServiceImpl(BotAdapter botAdapter) {
        this.botAdapter = botAdapter;
    }

    @Override
    public void chooseAction(String command, Message message) {
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
                        = botAdapter.getOrganization("/organization?name={name}", messageString);
                System.out.println(organizations);
                return getConclusion(message, statusChat, organizations);
            }
            case "/organization" -> {
                List<OrganizationDto> organizations
                        = botAdapter.getOrganizations("/organization?pageNo=0&pageSize=10&sortBy={rate}", messageString);
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
