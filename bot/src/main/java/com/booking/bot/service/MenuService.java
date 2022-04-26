package com.booking.bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Map;

public interface MenuService {
    InlineKeyboardMarkup getKeyboard(Map<Long,String> chatState, Message message, String command);
}
