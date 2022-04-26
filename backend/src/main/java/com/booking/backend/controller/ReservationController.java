package com.booking.backend.controller;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public void updateReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.updateWithNewBooking(reservationDto);
    }
}
