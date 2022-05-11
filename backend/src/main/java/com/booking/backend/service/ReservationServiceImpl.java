package com.booking.backend.service;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.mapper.ReservationMapper;
import com.booking.backend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final BookingService bookingService;

    @Override
    @Transactional
    public void updateWithNewBooking(ReservationDto reservationDto) {
        bookingService.addNewBooking(reservationDto.bookingDto());
        reservationRepository.save(reservationMapper.convert(reservationDto));
    }
}
