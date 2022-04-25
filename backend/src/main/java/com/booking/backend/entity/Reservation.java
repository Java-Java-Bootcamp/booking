package com.booking.backend.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer beginning;
    private Integer ending;
    private String date;

    @ManyToOne
    private SomeObject someObject;

    @ManyToOne
    private Booking booking;

}
