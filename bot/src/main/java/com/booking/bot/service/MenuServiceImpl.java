package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.state.Context;
import com.booking.bot.state.Stage;
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
                .callbackData("TYPE")
                .build());
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getOrganizationTypeKeyboard(Context context) {
        context.setStage(Stage.ORGANIZATIONS);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        botAdapter.getAllTypesOrganizations().forEach(typeOrganization -> rowList.add(List.of(InlineKeyboardButton.builder()
                .text(typeOrganization)
                .callbackData(typeOrganization)
                .build())));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("MAIN")
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup getChoiceOrganizationKeyboard(Context context) {
        //TODO: сделать кнопки страниц
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        System.out.println(context.getCallbackData());
        Page<OrganizationDto> organizationsByTypePage = botAdapter.getAllOrganizationsByType(context.getCallbackData());
        List<InlineKeyboardButton> pagesButtonRow = new ArrayList<>();
        for (int i = 1; i <= organizationsByTypePage.getTotalPages(); i++) {
            pagesButtonRow.add(InlineKeyboardButton.builder().text(String.valueOf(i)).callbackData(String.valueOf(i)).build());
        }
        organizationsByTypePage.getContent().forEach(organizationDto -> rowList.add(
                List.of(InlineKeyboardButton.builder()
                        .text(organizationDto.name())
                        .callbackData(organizationDto.id().toString())
                        .build())));
        rowList.add(pagesButtonRow);
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Назад")
                .callbackData("TYPE")
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text("<< Главное меню")
                .callbackData("MENU")
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
