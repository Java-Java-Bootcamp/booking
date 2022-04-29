package com.booking.bot.service;

import com.booking.bot.state.Context;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface MenuService {
    InlineKeyboardMarkup getMainKeyboard();
    InlineKeyboardMarkup getOrganizationTypeKeyboard(Context context);
}
