package com.booking.backend.controller;


import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Organization;
import com.booking.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/bookingsss")
    public List<Organization> getAll(@RequestParam Integer limit,
                                     @RequestParam Integer offset,
                                     @RequestParam String sortBy) {
        return organizationService.getSortedOrganization(limit, offset, sortBy);
    }
}
