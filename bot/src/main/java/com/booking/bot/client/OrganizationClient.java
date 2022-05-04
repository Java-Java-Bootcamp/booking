package com.booking.bot.client;

import com.booking.bot.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// TODO: url убрать в application.properties
@FeignClient(url = "localhost:8080", name = "organization-client")
public interface OrganizationClient {

    @GetMapping("/organization")
    Page<OrganizationDto> getAllOrganizations(Pageable pageable);

    @GetMapping("/organization/type/{type}?size=5")
    Page<OrganizationDto> getAllOrganizationsByType(@PathVariable String type,
                                                    Pageable pageable);
    @GetMapping("/organization/type")
    List<String> getAllTypesOrganizations();

    @GetMapping("/organization/{id}")
    OrganizationDto getById(@PathVariable Long id);

    @PostMapping("/organization")
    void addNewBooking(@RequestBody OrganizationDto organizationDto);
}
