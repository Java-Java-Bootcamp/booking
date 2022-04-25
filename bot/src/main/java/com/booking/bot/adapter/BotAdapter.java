package com.booking.bot.adapter;

import com.booking.bot.client.BookingClient;
import com.booking.bot.client.PersonClient;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingAdapter {

    private final BookingClient bookingClient;
    private final PersonClient personClient;

    public void addPerson(PersonDto personDto, String uri) {
        personClient.addNewBooking(personDto);
        // todo: обрабатывать ошибки
    }


    public List<OrganizationDto> getOrganization(String uri, String organizationName) {

        return Arrays.asList(Objects.requireNonNull(client.get().uri(uri, organizationName)
                .retrieve().bodyToMono(OrganizationDto[].class).share().block()));
    }

    public List<OrganizationDto> getOrganizations(String uri, String sortBy) {
        Mono<OrganizationDto[]> organizationDtoMono
                = client.get()
                .uri(uri, sortBy)
                .retrieve()
                .bodyToMono(OrganizationDto[].class);
        return Arrays.stream(Objects.requireNonNull(organizationDtoMono.share().block())).collect(Collectors.toList());
    }

}
