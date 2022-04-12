package com.booking.backend.dto;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Integer numbersOfTables,
                              Double averageCheck,
                              Double rating) {

}
