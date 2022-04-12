package com.booking.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Booking {
    //нужны ли нам в букинге время записи, если время записи есть в reservation?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer beginning;

    private Integer ending;

    private Integer numbersOfGuests;

    private boolean booked;

    @ManyToOne
    private User user;
    // создать команду создающую юзера

    @ManyToOne
    private Organization organization;


}
