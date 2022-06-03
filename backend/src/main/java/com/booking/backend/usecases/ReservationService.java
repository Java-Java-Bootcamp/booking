package com.booking.backend.usecases;

import com.booking.backend.entities.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final BookingService bookingService;

    @Transactional
    public void updateWithNewBooking(ReservationDto reservationDto) {
        bookingService.addNewBooking(reservationDto.bookingDto());
        reservationRepository.save(reservationMapper.convert(reservationDto));
    }
}
