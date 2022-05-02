package com.booking.bot.adapter;

import com.booking.bot.client.OrganizationClient;
import com.booking.bot.client.PersonClient;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
//import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@RequiredArgsConstructor
public class BotAdapter {

    private final PersonClient personClient;
    private final OrganizationClient organizationClient;

    public BotAdapter(PersonClient personClient, OrganizationClient organizationClient) {
        this.personClient = personClient;
        this.organizationClient = organizationClient;
    }

    public void addPerson(PersonDto personDto) {
        personClient.addNewBooking(personDto);
    }
    public List<String> getAllTypesOrganizations() {
        return organizationClient.getAllTypesOrganizations();
    }
    //@TODO: Пофиксить ошибку
    public List<OrganizationDto> getAllOrganizations(){
        return organizationClient.getAllOrganizations(Pageable.unpaged()).getContent();
    }
//    public List<OrganizationDto> getAllOrganizationsByType(String type) {
//        return organizationClient.getAllOrganizationsByType(type,Pageable.unpaged()).getContent();
//    }
    public OrganizationDto getOrganizationById(String id) {
        return organizationClient.getById(Long.parseLong(id));
    }
}
