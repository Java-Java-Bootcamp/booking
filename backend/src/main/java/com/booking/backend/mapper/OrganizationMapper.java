package com.booking.backend.mapper;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrganizationMapper {

    OrganizationDto convert(Organization organization);
    Organization convert(OrganizationDto organizationDto);

}
