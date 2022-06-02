package com.booking.bot.infrastructure;

import com.booking.bot.view.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// TODO: url убрать в application.properties
@FeignClient(url = "localhost:8080", name = "organization-controller")
public interface OrganizationClient {

    @GetMapping("/organization")
    Page<OrganizationDto> findAll(Pageable pageable);

    @GetMapping("/organization/type/{type}?size=10&page={page}")
    Page<OrganizationDto> findAllByType(@PathVariable String type,
                                                    @PathVariable Integer page,
                                                    Pageable pageable);

    @GetMapping("/organization/type")
    List<String> findAllTypes();

    @GetMapping("/organization/{id}")
    OrganizationDto getById(@PathVariable Long id);

    @PostMapping("/organization")
    void addNewBooking(@RequestBody OrganizationDto organizationDto);
}
