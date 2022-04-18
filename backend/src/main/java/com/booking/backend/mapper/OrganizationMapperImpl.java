package com.booking.backend.mapper;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDto convertFromOrganizationToOrganizationDto(Organization organization) {
        return new OrganizationDto(organization.getId(), organization.getName(),
                organization.getSchedule(), organization.getAverageCheck(),
                organization.getRating());
    }

    @Override
    public Organization convertFromOrganizationDtoToOrganization(OrganizationDto organizationDto) {
        return new Organization(organizationDto.id(), organizationDto.name(),
                organizationDto.schedule(), organizationDto.averageCheck(),
                organizationDto.rating());
    }
}
