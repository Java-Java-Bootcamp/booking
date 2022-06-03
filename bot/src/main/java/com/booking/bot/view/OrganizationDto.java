package com.booking.bot.view;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Double averageCheck,
                              Double rating,
                              String typeOrganization) {
}
