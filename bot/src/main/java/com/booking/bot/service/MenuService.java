package com.booking.bot.service;

import com.booking.bot.state.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface MenuService {
    InlineKeyboardMarkup getMainKeyboard(Context context) throws JsonProcessingException;

    InlineKeyboardMarkup getOrganizationTypeKeyboard(Context context);

    InlineKeyboardMarkup getChoiceOrganizationKeyboard(Context context);

    InlineKeyboardMarkup getDescriptionOrganizationKeyboard(Context context);
}
