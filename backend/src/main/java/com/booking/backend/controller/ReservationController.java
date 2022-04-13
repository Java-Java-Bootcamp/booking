package com.booking.backend.controller;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.dto.ReservationDto;
import com.booking.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    @RequestMapping(value = "/reservation", params = {"reservationId"})
    public ReservationDto getReservationById(@RequestParam Long reservationId) {
        return reservationService.findReservationById(reservationId);
    }
}
