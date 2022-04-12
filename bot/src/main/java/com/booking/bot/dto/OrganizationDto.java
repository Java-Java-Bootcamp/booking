package com.booking.bot.dto;

import com.booking.bot.entity.Cuisine;
import com.booking.bot.entity.Reservation;

import java.util.List;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Integer numbersOfTables,
                              Double averageCheck,
                              Double rating,
                              List<Reservation> reservationsList,
                              List<Cuisine> cuisines) {
}
