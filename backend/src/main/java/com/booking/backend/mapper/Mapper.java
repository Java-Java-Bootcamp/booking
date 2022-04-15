package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.Person;

import java.util.Optional;

public interface Mapper {

    BookingDto convertFromBookingToBookingDto(Booking booking);

    Booking convertFromBookingDtoToBooking(BookingDto bookingDto);

    Person convertFromPersonDtoToPerson(PersonDto personDto);

    PersonDto convertFromPersonToPersonDto(Optional<Person> person);

    OrganizationDto convertFromOrganizationToOrganizationDto(Organization organization);

    Organization convertFromOrganizationDtoToOrganization(OrganizationDto organizationDto);

}
