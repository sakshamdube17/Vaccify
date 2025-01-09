package com.example.Vaccination_booking_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    String name;

    @ManyToOne
    @JoinColumn
    VaccinationCenter vaccinationCenter;

}
