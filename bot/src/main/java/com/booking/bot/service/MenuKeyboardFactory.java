package com.booking.bot.service;

import com.booking.bot.view.OrganizationDto;
import com.booking.bot.state.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuKeyboardFactory {
    private final OrganizationService organizationService;

    private final String MAIN_MENU_TEXT_BUTTON = "<< Главное меню";
    private final String BACK_TEXT_BUTTON = "<< Назад";
    public InlineKeyboardMarkup createMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder()
                .text("Поиск")
                .callbackData("TYPE:null")
                .build());
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createOrganizationTypeKeyboard() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        organizationService.findAllTypes().forEach(typeOrganization -> rowList.add(List.of(InlineKeyboardButton.builder()
                .text(typeOrganization)
                .callbackData("ORGANIZATIONS:" + typeOrganization)
                .build())));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(MAIN_MENU_TEXT_BUTTON)
                .callbackData("MAIN:null")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup createChoiceOrganizationKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        Page<OrganizationDto> organizationsByTypePage = organizationService.findAllByType(context.getType(), context.getPage());
        List<InlineKeyboardButton> pagesButtonRow = new ArrayList<>();
        for (int i = 0; i < organizationsByTypePage.getTotalPages() && organizationsByTypePage.getTotalPages() != 1; i++) {
            pagesButtonRow.add(InlineKeyboardButton.builder()
                    .text(String.valueOf(i + 1))
                    .callbackData("PAGE:" + i)
                    .build());
        }
        organizationsByTypePage.getContent().forEach(organizationDto -> rowList.add(
                List.of(InlineKeyboardButton.builder()
                        .text(organizationDto.name())
                        .callbackData("DESCRIPTION:" + organizationDto.id().toString())
                        .build())));
        rowList.add(pagesButtonRow);
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(BACK_TEXT_BUTTON)
                .callbackData("TYPE:null")
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(MAIN_MENU_TEXT_BUTTON)
                .callbackData("MAIN:null")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup createDescriptionOrganizationKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(BACK_TEXT_BUTTON)
                .callbackData("ORGANIZATIONS:" + context.getType())
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(MAIN_MENU_TEXT_BUTTON)
                .callbackData("MAIN:null")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }
}
