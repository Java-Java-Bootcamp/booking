package com.booking.backend.service;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Reservation;
import com.booking.backend.mapper.BookingMapper;
import com.booking.backend.mapper.ReservationMapper;
import com.booking.backend.repository.BookingRepository;
import com.booking.backend.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private BookingService bookingService;


    @Override
    public void updateWithNewBooking(ReservationDto reservationDto) {
        bookingService.addNewBooking(reservationDto.bookingDto());
        reservationRepository.save(reservationMapper.convertFromReservationDtoToReservation(reservationDto));
    }
}
