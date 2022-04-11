package com.booking.bot.dto;

import com.booking.bot.entity.Cuisine;
import com.booking.bot.entity.Reservation;

import java.util.List;

public record BookingDto(Long id,
                         String organizationName,
                         Double rate,
                         Double bill,
                         List<Cuisine> cuisineOfOrganization,
                         List<Reservation> slotsOfReservation) {
}