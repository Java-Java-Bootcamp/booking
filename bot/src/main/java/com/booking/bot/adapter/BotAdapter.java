package com.booking.bot.adapter;

import com.booking.bot.client.OrganizationClient;
import com.booking.bot.client.PersonClient;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<String> getAllTypesOrganizations() {
        return organizationClient.getAllTypesOrganizations();
    }

    public List<OrganizationDto> getAllOrganizations() {
        return organizationClient.getAllOrganizations(Pageable.unpaged()).getContent();
    }

    public Page<OrganizationDto> getAllOrganizationsByType(String type, Integer page) {
        return organizationClient.getAllOrganizationsByType(type, page, Pageable.unpaged());
    }

    public OrganizationDto getOrganizationById(String id) {
        return organizationClient.getById(Long.parseLong(id));
    }
}
