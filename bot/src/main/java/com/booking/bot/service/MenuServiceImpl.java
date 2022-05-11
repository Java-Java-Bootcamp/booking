package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
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
public class MenuServiceImpl implements MenuService {
    private final BotAdapter botAdapter;

    @Override
    public InlineKeyboardMarkup getMainKeyboard(Context context) {
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

    public InlineKeyboardMarkup getOrganizationTypeKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        botAdapter.getAllTypesOrganizations().forEach(typeOrganization -> rowList.add(List.of(InlineKeyboardButton.builder()
                .text(typeOrganization)
                .callbackData("ORGANIZATIONS:" + typeOrganization)
                .build())));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("MAIN:null")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup getChoiceOrganizationKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        Page<OrganizationDto> organizationsByTypePage = botAdapter.getAllOrganizationsByType(context.getType(), context.getPage());
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
                .text("<< Назад")
                .callbackData("TYPE:null")
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("MAIN:null")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup getDescriptionOrganizationKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Назад")
                .callbackData("ORGANIZATIONS:" + context.getType())
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("MAIN:null")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }
}
