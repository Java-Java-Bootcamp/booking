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
                return choiceOrganizationKeyboard(command,0);
            }
            case "description of the organization" -> {
                return descriptionOrganizationKeyboard();
            }
            default -> {
                return null;
            }
        }
    }

    private InlineKeyboardMarkup mainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder()
                .text("Поиск")
                .callbackData("/find")
                .build());
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
                .forEach(typeOrganization -> rowList.add(List.of(InlineKeyboardButton.builder()
                        .text(typeOrganization)
                        .callbackData(typeOrganization)
                        .build())));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("/start")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup choiceOrganizationKeyboard(String type,Integer page) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<OrganizationDto> organizationsByType = botAdapter.getOrganizationsByType(type);
        long countOrganizations = organizationsByType.stream()
                .filter(o -> o.typeOrganization().equals(type))
                .count();
        List<InlineKeyboardButton> pagesButtonRow = new ArrayList<>();
        long pages = countOrganizations >= 10 && countOrganizations % 10 == 0 ? countOrganizations / 10 : countOrganizations / 10 + 1;
        for (int i = 1; i <= pages; i++) {
            pagesButtonRow.add(InlineKeyboardButton.builder().text(String.valueOf(i)).callbackData(String.valueOf(i)).build());
        }
        organizationsByType.forEach(organizationDto -> rowList.add(
                List.of(InlineKeyboardButton.builder()
                        .text(organizationDto.name())
                        .callbackData(organizationDto.id().toString())
                        .build())));
        rowList.add(pagesButtonRow);
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Назад")
                .callbackData("/find")
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("/start")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    private InlineKeyboardMarkup descriptionOrganizationKeyboard() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Назад")
                .callbackData("back from description")
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("/start")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }
}
