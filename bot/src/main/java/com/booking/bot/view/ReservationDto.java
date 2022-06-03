package com.booking.bot.view;

public record ReservationDto(Long id,
                             Integer beginning,
                             Integer ending,
                             String date,
                             SomeObjectDto someObjectDto,
                             BookingDto bookingDto) {
}
