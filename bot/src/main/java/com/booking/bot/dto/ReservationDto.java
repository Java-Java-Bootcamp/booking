package com.booking.bot.dto;

public record ReservationDto(Long id,
                             Integer beginning,
                             Integer ending,
                             String date,
                             SomeObjectDto someObjectDto,
                             BookingDto bookingDto
                             ) {
}
