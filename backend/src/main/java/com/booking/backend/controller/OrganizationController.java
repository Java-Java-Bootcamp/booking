package com.booking.backend.controller;


import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.TypeOrganization;
import com.booking.backend.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organization")
    Page<OrganizationDto> getAllOrganizations(Pageable pageable) {
        return organizationService.getAllOrganizations(pageable);
    }

    @GetMapping("/organization/type/{type}")
    Page<OrganizationDto> getAllOrganizationsByType(@PathVariable TypeOrganization type,
                                                    Pageable pageable) {
        return organizationService.getOrganizationsByType(pageable, type);
    }

    @GetMapping("/organization/type")
    List<TypeOrganization> getAllTypesOrganizations() {
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
