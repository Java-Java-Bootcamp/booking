package com.booking.bot.service;

import com.booking.bot.dto.BookingDto;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.UserDto;
import com.booking.bot.entity.Reservation;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface BookingService {

    Reservation createReservationDtoForBooking(Long id, Integer beginning, Integer ending, Integer numbersOfTable);

    void createButtonsForReservations(Message message, OrganizationDto organizationDto);

    OrganizationDto setChangedReservationToOrganization(OrganizationDto organizationDto, String reservationId);

    public BookingDto createBookingForSending(UserDto userDto, OrganizationDto organizationDto, Reservation reservation);

}
