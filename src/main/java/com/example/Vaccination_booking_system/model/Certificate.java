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
public class Certificate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    @OneToOne
    @JoinColumn
    Person person;
}
