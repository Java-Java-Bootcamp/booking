package com.booking.backend.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String schedule;

    private Double averageCheck;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private TypeOrganization typeOrganization;
}
