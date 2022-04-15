package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {

    @Override
    public BookingDto convertFromBookingToBookingDto(Booking booking) {
        return new BookingDto(booking.getId(), null, null);
    }

    @Override
    public Booking convertFromBookingDtoToBooking(BookingDto bookingDto) {
        return new Booking(bookingDto.id(), convertFromPersonDtoToPerson(bookingDto.person()));
    }

    @Override
    public Person convertFromPersonDtoToPerson(PersonDto personDto) {
        return new Person(personDto.id(), personDto.name());
    }

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
