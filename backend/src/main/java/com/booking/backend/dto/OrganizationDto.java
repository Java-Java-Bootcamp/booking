package com.booking.backend.dto;

import com.booking.backend.entity.Cuisine;
import com.booking.backend.entity.Reservation;

import java.util.List;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Integer numbersOfTables,
                              Double averageCheck,
                              Double rating,
                              List<Reservation> reservationsList,
                              List<Cuisine> cuisines) {

}
