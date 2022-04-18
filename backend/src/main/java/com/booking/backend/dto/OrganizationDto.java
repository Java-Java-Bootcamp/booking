package com.booking.backend.dto;

import com.booking.backend.entity.TypeOrganization;

public record OrganizationDto(Long id,
                              String name,
                              String schedule,
                              Double averageCheck,
                              Double rating,
                              TypeOrganization typeOrganization) {
}
