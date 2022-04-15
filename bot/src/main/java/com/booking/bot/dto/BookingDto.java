package com.booking.bot.dto;

public record BookingDto(Long id,
                         User user,
                         Integer numberOfGuests) {
}