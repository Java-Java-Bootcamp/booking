package com.booking.bot.adapter;

import com.booking.bot.client.BookingClient;
import com.booking.bot.client.OrganizationClient;
import com.booking.bot.client.PersonClient;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BotAdapter {

    private final BookingClient bookingClient;
    private final PersonClient personClient;
    private final OrganizationClient organizationClient;

    public void addPerson(PersonDto personDto) {
        personClient.addNewBooking(personDto);
    }


    public List<OrganizationDto> getOrganization(String organizationName) {
        return organizationClient.getAll(organizationName);
    }

    public List<OrganizationDto> getOrganizations(String sortBy) {
        return organizationClient.getAll(0, 10, sortBy);
    }

    public List<OrganizationDto> getOrganizations() {
        return organizationClient.getAllOrganization();
    }

    public OrganizationDto getOrganizationById(String id) {
        return organizationClient.getById(Long.parseLong(id));
    }

}
