package com.booking.bot.adapter;

import com.booking.bot.dto.BookingDto;
import com.booking.bot.dto.OrganizationDto;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
@Data
public class BookingAdapter {

    private final WebClient client;

    public BookingAdapter() {
        this.client = WebClient.create("http://localhost:8080");
    }

    public void updateOrganizationWithNewReservation(OrganizationDto organizationDto, String uri) {
        client
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(organizationDto), OrganizationDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .share()
                .block();
    }

    public void addBooking(BookingDto bookingDto, String uri) {
        client
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(bookingDto), BookingDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .share()
                .block();
    }


    public OrganizationDto getOrganization(String uri, String organizationName) {
        Mono<OrganizationDto[]> organizationDtoMono
                = client.get().uri(uri, organizationName).retrieve().bodyToMono(OrganizationDto[].class);
        return Arrays.stream(organizationDtoMono.share().block()).findFirst().get();
    }

    public OrganizationDto[] getArrayOrganizations(String uri) {
        Mono<OrganizationDto[]> organizationDtoMono = client.get().uri("/organization")
                .retrieve().bodyToMono(OrganizationDto[].class);
        OrganizationDto[] organizationDto = organizationDtoMono.share().block();
        return organizationDto;
    }
}
