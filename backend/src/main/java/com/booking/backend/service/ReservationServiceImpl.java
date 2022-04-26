package com.booking.backend.service;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.mapper.ReservationMapper;
import com.booking.backend.repository.ReservationRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private BookingService bookingService;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper, BookingService bookingService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.bookingService = bookingService;
    }

    @Override
    @Transactional
    public void updateWithNewBooking(ReservationDto reservationDto) {
        bookingService.addNewBooking(reservationDto.bookingDto());
        reservationRepository.save(reservationMapper.convert(reservationDto));
    }
}
