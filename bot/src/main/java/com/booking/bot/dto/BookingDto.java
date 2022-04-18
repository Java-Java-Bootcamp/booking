package com.booking.bot.dto;

public record BookingDto(Long id,
                         PersonDto person,
                         Integer numberOfGuests) {
}