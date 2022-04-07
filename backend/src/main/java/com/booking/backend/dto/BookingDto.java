package com.booking.backend.dto;

import com.booking.backend.entity.Cuisine;

import java.util.List;

public record BookingDto(Long id,
                         String organization,
                         Double rate,
                         Double bill,
                         List<Cuisine> cuisine,
                         String schedule) {
//TODO: сделать все поля, чтобы получать разные объекты
}
