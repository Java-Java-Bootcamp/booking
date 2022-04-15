package com.booking.bot.dto;


import java.util.List;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Double averageCheck,
                              Double rating) {

}
