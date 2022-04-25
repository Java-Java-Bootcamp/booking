package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final BotAdapter botAdapter;

    @Override
    public InlineKeyboardMarkup getKeyboard(Map<Long, String> chatState, Message message, String command) {
        switch (chatState.get(message.getFrom().getId())) {
            case "main menu" -> {
                return mainKeyboard();
            }
            case "choice of organization type" -> {
                chatState.put(message.getFrom().getId(), "choice of organizations");
                return organizationTypeKeyboard();
            }
            case "choice of organizations" -> {
                chatState.put(message.getFrom().getId(), "description of the organization");
                return choiceOrganizationKeyboard(command);
            }
            case "description of the organization" -> {
                return descriptionOrganizationKeyboard(command);
            }
            default -> {
                return null;
            }
        }
    }

    private InlineKeyboardMarkup mainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder().text("Поиск").callbackData("/find").build());
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup organizationTypeKeyboard() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        botAdapter.getOrganizations().stream()
                .map(OrganizationDto::typeOrganization)
                .distinct()
                .forEach(typeOrganization -> rowList.add(List.of(InlineKeyboardButton.builder().text(typeOrganization).callbackData(typeOrganization).build())));
        rowList.add(List.of(InlineKeyboardButton.builder().text("<< Главное меню").callbackData("/start").build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    private InlineKeyboardMarkup choiceOrganizationKeyboard(String type) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        botAdapter.getOrganizations().stream()
                .filter(o -> o.typeOrganization().equals(type))
                .forEach(organizationDto -> rowList.add(List.of(InlineKeyboardButton.builder().text(organizationDto.name()).callbackData(organizationDto.id().toString()).build())));
        rowList.add(List.of(InlineKeyboardButton.builder().text("<< Назад").callbackData("/find").build()));
        rowList.add(List.of(InlineKeyboardButton.builder().text("<< Главное меню").callbackData("/start").build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    private InlineKeyboardMarkup descriptionOrganizationKeyboard(String id) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(List.of(InlineKeyboardButton.builder().text("<< Назад").callbackData("back from description").build()));
        rowList.add(List.of(InlineKeyboardButton.builder().text("<< Главное меню").callbackData("/start").build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }
}
