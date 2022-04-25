package com.booking.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SomeObject {

    @Id
    private Long id;
    private String description;

    @ManyToOne
    private Organization organization;
}
