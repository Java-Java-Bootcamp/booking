package com.booking.backend.controller;


import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/organization")
    @RequestMapping(value = "/organization", params = {"pageNo","pageSize","sortBy"})
    public List<OrganizationDto> getAll(@RequestParam Integer pageNo,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String sortBy) {
        return organizationService.getSortedOrganization(pageNo, pageSize, sortBy);
    }

    @GetMapping("/organization")
    @RequestMapping(value = "/organization", params = "name")
    public List<OrganizationDto> getAll(@RequestParam String name) {
        return organizationService.findAllByName(name);
    }

    @GetMapping("/organization")
    @RequestMapping(value = "/organization", params = "id")
    public OrganizationDto getById(@RequestParam Long id) {
        return organizationService.getById(id);
    }

    @GetMapping("/organization")
    @RequestMapping(value = "/organization")
    public List<OrganizationDto> getAllOrganization() {
        return organizationService.getAll();
    }

    @PostMapping("/organization")
    public void addNewBooking(@RequestBody OrganizationDto organizationDto) {
        organizationService.updateOrganization(organizationDto);
    }
}
