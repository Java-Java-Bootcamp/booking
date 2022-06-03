package com.booking.bot.model;

import com.booking.bot.state.Stage;
import com.booking.bot.view.OrganizationDto;

public final class TextConstants {
    public static final String CHOOSE_TYPE = "Выбери организацию:";
    public static final String MAIN_MENU_BUTTON = "<< Главное меню";
    public static final String BACK_BUTTON = "<< Назад";

    public static String createWelcome(String userName) {
        return String.format("Hi,  %s! Сервис по бронированию.", userName);
    }

    public static String createDescription(OrganizationDto organization) {
        return String.format("""
                        %s:
                        Рейтинг = %.1f
                        Средний чек = %.2f
                        Расписание = %s""",
                organization.name(),
                organization.rating(),
                organization.averageCheck(),
                organization.schedule()
        );
    }

    public static String createCallbackData(Stage stage,String data) {
        return stage.name()+":"+data;
    }
    public static String createCallbackData(Stage stage) {
        return stage.name()+":"+"null";
    }

}
