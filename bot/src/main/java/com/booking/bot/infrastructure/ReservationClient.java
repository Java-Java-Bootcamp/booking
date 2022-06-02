package com.booking.bot.infrastructure;

import com.booking.bot.view.ReservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(url = "localhost:8080", name = "reservation-controller")
public interface ReservationClient {
    @PostMapping("/reservation")
    void updateReservation(@RequestBody ReservationDto reservationDto);
}
