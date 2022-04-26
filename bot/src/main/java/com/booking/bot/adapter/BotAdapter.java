package com.booking.bot.adapter;

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

    private final PersonClient personClient;
    private final OrganizationClient organizationClient;

    public void addPerson(PersonDto personDto) {
        personClient.addNewBooking(personDto);
    }

    public List<OrganizationDto> getSortedOrganizationsByType(Integer pageNo, Integer pageSize, String sortBy, String type) {
        return organizationClient.getSortByType(pageNo, pageSize, sortBy, type);
    }

    public List<OrganizationDto> getOrganizations() {
        return organizationClient.getAllOrganization();
    }

    public List<OrganizationDto> getOrganizationsByType(String type) {
        return organizationClient.getByType(type);
    }

    public OrganizationDto getOrganizationById(String id) {
        return organizationClient.getById(Long.parseLong(id));
    }

}
