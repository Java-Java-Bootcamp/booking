package com.booking.bot.dto;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Double averageCheck,
                              Double rating,
                              String typeOrganization) {}
