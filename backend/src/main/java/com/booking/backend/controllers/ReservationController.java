package com.booking.backend.controllers;

import com.booking.backend.usecases.ReservationDto;
import com.booking.backend.usecases.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public void updateReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.updateWithNewBooking(reservationDto);
    }
}
