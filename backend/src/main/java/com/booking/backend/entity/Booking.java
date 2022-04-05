package com.booking.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfBeginning;

    private LocalDate dateOfEnding;

    @OneToOne
    private User user;

    @OneToOne
    private Organization organization;


}
