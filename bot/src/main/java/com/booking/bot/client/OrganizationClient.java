package com.booking.bot.client;

import com.booking.bot.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: url убрать в application.properties
@FeignClient(url = "localhost:8080", name = "organization-client")
public interface OrganizationClient {

    @GetMapping("/organization/pageNo={pageNo},pageSize={pageSize},sortBy={sortBy}")
    List<OrganizationDto> getAll(@PathVariable Integer pageNo,
                                 @PathVariable Integer pageSize,
                                 @PathVariable String sortBy);

    @GetMapping("/organization/name={name}")
    List<OrganizationDto> getAll(@PathVariable String name);

    @GetMapping("/organization/id={id}")
    OrganizationDto getById(@PathVariable Long id);


    @GetMapping("/organization")
    List<OrganizationDto> getAllOrganization();

    @PostMapping("/organization")
    void addNewBooking(@RequestBody OrganizationDto organizationDto);
}
