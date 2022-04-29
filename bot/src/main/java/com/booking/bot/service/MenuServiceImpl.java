package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.state.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final BotAdapter botAdapter;

    public InlineKeyboardMarkup getMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder()
                .text("Поиск")
                //@TODO: сделать сериализацию-десериализацию объекта Context
                .callbackData("/find")
                .build());
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getOrganizationTypeKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        botAdapter.getAllOrganizations().stream()
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

    public InlineKeyboardMarkup getChoiceOrganizationKeyboard(String type, Integer page) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<OrganizationDto> organizationsByType = botAdapter.getAllOrganizationsByType(type);
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

    private InlineKeyboardMarkup getDescriptionOrganizationKeyboard() {
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
