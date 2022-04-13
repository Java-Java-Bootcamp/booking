package com.booking.bot.dto;

import com.booking.bot.entity.Cuisine;
import com.booking.bot.entity.Reservation;

import java.util.List;

public record BookingDto(Reservation reservation,
                         UserDto userDto,
                         Long organizationId)
{

    //в бэкенде получить эту сущность
}