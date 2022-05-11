package com.booking.bot.client;

import com.booking.bot.dto.ReservationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReservationClient {
    @PostMapping("/reservation")
    void updateReservation(@RequestBody ReservationDto reservationDto);
}
