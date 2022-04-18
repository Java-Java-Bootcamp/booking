package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.Person;

import java.util.Optional;

public interface OrganizationMapper {

    OrganizationDto convertFromOrganizationToOrganizationDto(Organization organization);

    Organization convertFromOrganizationDtoToOrganization(OrganizationDto organizationDto);

}
