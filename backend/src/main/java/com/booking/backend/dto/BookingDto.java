package com.booking.backend.dto;

import com.booking.backend.entity.Person;

public record BookingDto(Long id,
                         Person person,
                         Integer numberOfGuests) {
}
