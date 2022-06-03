package com.booking.bot.model;

import com.booking.bot.state.Command;
import com.booking.bot.state.Context;
import com.booking.bot.state.Stage;
import com.booking.bot.view.OrganizationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MenuKeyboardFactory {
    private final OrganizationService organizationService;

    public InlineKeyboardMarkup createMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder()
                .text("Поиск")
                .callbackData(TextConstants.createCallbackData(Stage.TYPE))
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
                .callbackData(TextConstants.createCallbackData(Stage.ORGANIZATIONS,typeOrganization))
                .build())));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(TextConstants.MAIN_MENU_BUTTON)
                .callbackData(TextConstants.createCallbackData(Stage.MAIN))
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
                    .callbackData(TextConstants.createCallbackData(Stage.PAGE,String.valueOf(i)))
                    .build());
        }
        organizationsByTypePage.getContent().forEach(organizationDto -> rowList.add(
                List.of(InlineKeyboardButton.builder()
                        .text(organizationDto.name())
                        .callbackData(TextConstants.createCallbackData(Stage.DESCRIPTION,organizationDto.id().toString()))
                        .build())));
        rowList.add(pagesButtonRow);
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(TextConstants.BACK_BUTTON)
                .callbackData(TextConstants.createCallbackData(Stage.TYPE))
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(TextConstants.MAIN_MENU_BUTTON)
                .callbackData(TextConstants.createCallbackData(Stage.MAIN))
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public InlineKeyboardMarkup createDescriptionOrganizationKeyboard(Context context) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(TextConstants.BACK_BUTTON)
                .callbackData(TextConstants.createCallbackData(Stage.ORGANIZATIONS,context.getType()))
                .build()));
        rowList.add(List.of(InlineKeyboardButton.builder()
                .text(TextConstants.MAIN_MENU_BUTTON)
                .callbackData(TextConstants.createCallbackData(Stage.MAIN))
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }
}
