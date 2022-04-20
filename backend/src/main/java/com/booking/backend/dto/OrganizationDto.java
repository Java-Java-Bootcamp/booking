package com.booking.backend.dto;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Double averageCheck,
                              Double rating) {

}
