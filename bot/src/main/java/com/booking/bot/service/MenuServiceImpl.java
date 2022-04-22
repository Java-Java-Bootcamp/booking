package com.booking.bot.service;

import com.booking.bot.adapter.BotAdapter;
import com.booking.bot.dto.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    @Autowired
    private BotAdapter botAdapter;

    public MenuServiceImpl(BotAdapter botAdapter) {
        this.botAdapter = botAdapter;
    }

    @Override
    public InlineKeyboardMarkup getKeyboard(Map<Long, String> chatState, Message message, String command) {
        switch (chatState.get(message.getFrom().getId())) {
            case "/start" -> {
                return mainKeyboard();
            }
            case "start > types" -> {
                chatState.put(message.getFrom().getId(), "types > organizations");
                return organisationTypesKeyboard();
            }
            case "types > organizations" -> {
                return organizationKeyboard(command);
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

    private InlineKeyboardMarkup organisationTypesKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<OrganizationDto> organizationDtoList = botAdapter.getOrganizations("/organization");
        List<String> typesList = new ArrayList<>();
        for (OrganizationDto oD : organizationDtoList) {
            if (!typesList.contains(oD.typeOrganization())) {
                typesList.add(oD.typeOrganization());
            }
        }
        for(String type : typesList) {
            keyboardButtonsRow.add(InlineKeyboardButton.builder().text(type).callbackData(type).build());
        }
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder().text("<< Главное меню").callbackData("/start").build());
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup organizationKeyboard(String command) {
        List<InlineKeyboardButton> keyboardButtonsRow;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<OrganizationDto> organizationDtoList = botAdapter.getOrganizations("/organization");
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (OrganizationDto oD : organizationDtoList) {
            if (oD.typeOrganization().equals(command)) {
                keyboardButtonsRow = new ArrayList<>();
                keyboardButtonsRow.add(InlineKeyboardButton.builder().text(oD.name()).callbackData(oD.id().toString()).build());
                rowList.add(keyboardButtonsRow);
            }
        }
        keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder().text("<< Назад").callbackData("/find").build());
        rowList.add(keyboardButtonsRow);
        keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(InlineKeyboardButton.builder().text("<< Главное меню").callbackData("/start").build());
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
