package com.booking.bot.service;

import com.booking.bot.dto.BookingDto;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

//    @Override
//    public Reservation createReservationDtoForBooking(Long id, Integer beginning, Integer ending, Integer numbersOfTable) {
//        return new Reservation(id, beginning, ending, numbersOfTable);
//    }

    @Override
    public void createButtonsForReservations(Message message, OrganizationDto organizationDto) {
//
//        List<Reservation> reservationDtos = organizationDto.reservationsList();
//        List<List<InlineKeyboardButton>> buttonsForReservation = new ArrayList<>();
//        reservationDtos.removeIf(reservation -> reservation.getNumbersOfTables() <= 0);
//        for (Reservation reservation : reservationDtos) {
//            buttonsForReservation.add(
//                    Arrays.asList(
//                            InlineKeyboardButton.builder()
//                                    .text("Доступное время: " + reservation.getBeginning() + "-"
//                                            + reservation.getEnding() + " Количество свободных столов: " + reservation.getNumbersOfTables())
//                                    .callbackData("Reservation:" + reservation.getId() + ":"
//                                            + reservation.getBeginning() + ":"
//                                            + reservation.getEnding() + ":"
//                                            + reservation.getNumbersOfTables())
//                                    .build()));
//        }
    }

    @Override
    public OrganizationDto setChangedReservationToOrganization(OrganizationDto organizationDto, String reservationId) {
//        List<Reservation> reservation = organizationDto.reservationsList();
//        Reservation reservation1 = reservation.stream().filter(e -> e.getId() == Long.valueOf(reservationId)).findFirst().get();
//        reservation1.setNumbersOfTables(reservation1.getNumbersOfTables() - 1);
//        Integer index = organizationDto.reservationsList().indexOf(reservation1);
//        organizationDto.reservationsList().set(index, reservation1);
        return organizationDto;

    }

//    @Override
//    public BookingDto createBookingForSending(PersonDto personDto, OrganizationDto organizationDto
////            , Reservation reservation
//    ) {
////        return new BookingDto(reservation, personDto, organizationDto);
//        return null;
//    }


}
