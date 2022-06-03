package com.booking.bot.view;

public record BookingDto(Long id,
                         PersonDto person,
                         Integer numberOfGuests) {
}