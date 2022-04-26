package com.booking.bot.client;

import com.booking.bot.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: url убрать в application.properties
@FeignClient(url = "localhost:8080", name = "organization-client")
public interface OrganizationClient {

    @RequestMapping(value = "/organization", params = {"pageNo", "pageSize", "sortBy"}, method = RequestMethod.GET)
    List<OrganizationDto> getSort(@RequestParam Integer pageNo,
                                  @RequestParam Integer pageSize,
                                  @RequestParam String sortBy);

    @RequestMapping(value = "/organization", params = {"pageNo", "pageSize", "sortBy", "type"}, method = RequestMethod.GET)
    <TypeOrganization> List<OrganizationDto> getSortByType(@RequestParam Integer pageNo,
                                                           @RequestParam Integer pageSize,
                                                           @RequestParam String sortBy,
                                                           @RequestParam TypeOrganization type);

    @RequestMapping(value = "/organization", params = {"name"}, method = RequestMethod.GET)
    List<OrganizationDto> getAll(@RequestParam String name);

    @RequestMapping(value = "/organization", params = {"id"}, method = RequestMethod.GET)
    OrganizationDto getById(@RequestParam Long id);

    @RequestMapping(value = "/organization", params = {"type"}, method = RequestMethod.GET)
    <TypeOrganization> List<OrganizationDto> getByType(@RequestParam TypeOrganization type);

    @GetMapping("/organization")
    List<OrganizationDto> getAllOrganization();

    @PostMapping("/organization")
    void addNewBooking(@RequestBody OrganizationDto organizationDto);
}
