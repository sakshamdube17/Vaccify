package com.example.Vaccination_booking_system.model;

import com.example.Vaccination_booking_system.Enum.Gender;
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
    @Column(nullable = false,unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @ManyToOne
    @JoinColumn
    VaccinationCenter vaccinationCenter;

}
