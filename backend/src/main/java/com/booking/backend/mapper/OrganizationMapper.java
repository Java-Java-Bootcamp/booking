package com.booking.backend.mapper;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;

public interface OrganizationMapper {

    OrganizationDto convertFromOrganizationToOrganizationDto(Organization organization);

    Organization convertFromOrganizationDtoToOrganization(OrganizationDto organizationDto);

}
