package com.booking.bot.adapter;

import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
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
public class BotAdapter {

    private final WebClient client;

    public BotAdapter() {
        this.client = WebClient.create("http://localhost:8080");
    }

    public void addPerson(PersonDto personDto, String uri) {
        client
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(personDto), PersonDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .share()
                .block();
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
    public List<OrganizationDto> getOrganizations(String uri) {
        Mono<OrganizationDto[]> organizationDtoMono
                = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(OrganizationDto[].class);
        return Arrays.stream(Objects.requireNonNull(organizationDtoMono.share().block())).collect(Collectors.toList());
    }
}
