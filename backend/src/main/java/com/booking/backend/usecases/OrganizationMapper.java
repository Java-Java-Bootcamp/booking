package com.booking.backend.usecases;

import com.booking.backend.entities.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationDto convert(Organization organization);

    Organization convert(OrganizationDto organizationDto);
}
