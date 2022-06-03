package com.booking.backend.controllers;


import com.booking.backend.entities.TypeOrganization;
import com.booking.backend.usecases.OrganizationDto;
import com.booking.backend.usecases.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organization")
    Page<OrganizationDto> findAll(Pageable pageable) {
        return organizationService.getAllOrganizations(pageable);
    }

    @GetMapping("/organization/type/{type}")
    Page<OrganizationDto> findAllByType(@PathVariable TypeOrganization type,
                                        Pageable pageable) {
        return organizationService.getOrganizationsByType(pageable, type);
    }

    @GetMapping("/organization/type")
    List<TypeOrganization> findAllTypes() {
        return organizationService.getAllTypesOrganizations();
    }

    @GetMapping("/organization/{id}")
    public OrganizationDto getById(@PathVariable Long id) {
        return organizationService.getById(id);
    }

    @PostMapping("/organization")
    public void addNewBooking(@RequestBody OrganizationDto organizationDto) {
        organizationService.updateOrganization(organizationDto);
    }
}
